library(testthat)

test_that("malformend_connection_string", {
  expect_error(atlas::atlas.connect("xxx"), "Could not connect to ATLAS instance");
})
