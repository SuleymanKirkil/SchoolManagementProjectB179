@DB
  Feature: Dean Creation Validation DB
    Scenario: User Validates Created Dean From DB
      Given User Set connection
      And User creates statememnt
      When User execute query for created dean
      Then validates result set
      And User terminate connection