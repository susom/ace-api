#' Connects to ATLAS instance
#'
#' Attempts to connect to ATLAS instance using URL:PORT
#' @param url url address of a running ATLAS instance, usually containing port information
#' @return data frame containing connection information used for all other accessory functions
#'
#' @examples
#' \donttest{atlas.connect("http://localhost:8080")}
#'
atlas.connect <- function(url) {
  tryCatch(status <-httr::GET(paste0(url, "/status")), error=function(e) print("Cannot connect to the specified ATLAS instance"))
  if (!exists("status")) {
    stop("Could not connect to ATLAS instance")
  }
  print(status)
  status <- httr::content(status, type="application/json")
  if (status$status != "OK") {
    stop("ATLAS instance instance error")
  }
  response <- data.frame(1)
  response$url <- url
  response$status <- status$status
  response$dataset <- status$datasetVersion
  response$code <- status$version
  print(paste("Connected to ",url, response$code, response$dataset))
  return (response)
}
