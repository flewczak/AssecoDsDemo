Assumptions:

- order of the fields was not specified in task and the library is not guaranteeing the ordering of fields.
  As a result default ordering was introduced based on natural order. 
  Implementation can be changed by providing custom Comparator
- There was no clear indication about how mapping should be done if
  - field annotated with @KoliberDescription will have different value in comment field than class of that type. 
    -> I assumed that value should be taken from field annotation
  - field annotated with @KoliberDescription will have different name than class of that type
    -> I assumed that value for mapping should be taken from field name
  - field had generic type with more than one type (for example Map)
    -> Current implementation is throwing error that operation is not yet supported 
  - field was not annotated with annotation
    -> I assumed that field should be ignored
  - field annotated with @KoliberDescription which class is not annotated with @KoliberDescription is invalid scenario
    -> Current implementation is throwing error that operation is not yet supported
