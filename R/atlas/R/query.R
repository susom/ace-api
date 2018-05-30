#' Queries ATLAS and returns a list of patient IDs
#'
#' @param connection connection object returned from connect(url) function
#' @param query ATLAS query
#' @param output_time equivalent to wrapping the query in OUTPUT() command. Togerther with patient IDs outputs each
#'                    time interval in patient's timeline when the query was evaluated as true
#' @return data frame containing patient IDs and time intervals (optional)
#'
#' @examples
#' \donttest{atlas.query(atlas.connection('http://localhost:8080'), 'ICD9=250.50')}
#' \donttest{atlas.query(atlas.connection('http://localhost:8080'), 'ICD9=250.50', TRUE)}
#'
#'
atlas.query <- function(connection, query, output_time=FALSE) {
  stopifnot(is.logical(output_time))
  request <- paste0('{"query":"', query, '"}')
  if (regexpr("output(\\s+)?\\(", tolower(query)) >= 0) {
    output_time = TRUE
  }
  request <- paste0('{"query":"', query, '", "returnTimeIntervals":',output_time, '}')
  response <- httr::POST(url = paste0(connection$url,'/query'), body = request)
  json_response <- httr::content(response, type="application/json")
  if (!is.null(json_response$errorMessage)) {
    stop(json_response$errorMessage)
  }
  index <- 1
  pids <- vector()
  startTime <- vector()
  endTime <- vector()
  for (i in json_response$patientIds) {
    val <- 1;
    for (j in i) {
      if (val == 1) {
        pids[index] <- j[1]
      } else if (val == 2) {
        startTime[index] <- j[1]
      } else if (val == 3) {
        endTime[index] <- j[1]
        index <- index + 1
        val <- 0;
      }
      if (output_time) {
        val <- val + 1
      } else {
        index <- index + 1
      }
    }
  }

  if (output_time) {
    result <- data.frame(pids, startTime, endTime)
    colnames(result) <- c("patientId", "startTime", "endTime")
  } else {
    result <- data.frame(pids)
    colnames(result) <- c("patientId")
  }

  return(result)
}

#' Queries ATLAS with a CSV() command and imports the contents of the csv into a data frame
#'
#' @param connection connection object returned from connect(url) function
#' @param query ATLAS CSV query
#' @param file_name if specified, stores the csv into the file_name, otherwise the temporary
#'                  file used to download the data will be deleted after the data.frame is generated
#' @return data frame containing CSV file
#'
#' @examples
#' \donttest{atlas.csv(atlas.connection('http://localhost:8080'), 'CSV("ICD9=250.50", CPT, LABS, ICD9)')}
#' \donttest{atlas.csv(atlas.connection('http://localhost:8080'), 'CSV("ICD9=250.50", CPT, LABS, ICD9)',
#'           '/output.csv')}

#'
atlas.csv <- function(connection, query, file_name=NULL) {
  if (!startsWith(tolower(trimws(query, which="both")), "csv(")) {
    stop("Not a CSV() query")
  }
  request <- paste0('{"query":"', gsub(pattern = '"', replacement = '\\\\"', x = query), '", "returnTimeIntervals": false}')
  print(request)
  response <- httr::POST(url = paste0(connection$url,'/query'), body = request)
  json_response <- httr::content(response, type="application/json")
  if (is.null(file_name)) {
    temp <- tempfile()
  } else {
    temp <- file_name
  }
  print("Querying ATLAS instance...")
  csv_content = httr::GET(url=paste0(connection$url,'/',json_response$exportLocation), write_disk(temp, overwrite=TRUE))
  print(paste0("Writing output into: ", temp))
  print("Importing data into R...")
  result = read.csv(temp, header=TRUE, sep='\t')
  if (is.null(file_name)) {
    print("Deleting temporary files...")
    file.remove(temp)
  }
  return(result)
}
