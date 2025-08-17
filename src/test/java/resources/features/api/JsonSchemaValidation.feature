Feature: JSON Schema Validation for Users

  @JSONSchemaValidation   @ApiTestcase8
  Scenario: Validate users response against schema
    Given I have the JSON schema
    When I send a GET request to users endpoint
    Then the response should match the JSON schema