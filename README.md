**JSON Parser**
This is a JSON parser as from Coding Challenges. This process involves parsing the JSON string, extracting the tokens, and then constructing the data structure based on the parsed tokens. at the presente state only parsing is made not structure.

**My Implementation**
This implementation is written in java with maven structure.
This implementation does not fulfill test 18, 25  37 of final tests (see StepFinalTest5,java), because I believe that should be accettable for test cases.

*source*
Original ideas of Lexer and Parser from literature.
The challenge: https://codingchallenges.fyi/challenges/challenge-json-parser/
Build Your Own JSON Parser.

**Last version**
Version 2.0 implements the AST tree for retrieval and navigation in JSON file.
For example:
```json
{
  "theme": "Default",
  "selectedAuthType": "oauth-personal",
  "preferredEditor": "vscode"
}
```
```text
JsonObjectNode node = parser.jObject(); // or use jPayload()
{
  "theme": "Default",
  "selectedAuthType": "oauth-personal",
  "preferredEditor": "vscode"
}
JsonObjectNode{children={selectedAuthType=JsonStringNode{oauth-personal}, preferredEditor=JsonStringNode{vscode}, theme=JsonStringNode{Default}}}
```