Feature: As a QE, I validate that I can connect to DB

  @db
  Scenario Outline: Validating the Database Connection
    Given is able to connect to database
    When User send the "<query>" to database
    Then Validate the <salary>
    Examples: Database query
      | query                             | salary |
      | select min(salary) from employees | 2100   |