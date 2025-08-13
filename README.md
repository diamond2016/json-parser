**JSON Parser**
A JSON parser challenge involves creating a program or function that can convert a JSON string into a corresponding data structure (like a Python dictionary or a JavaScript object). 
This process involves parsing the JSON string, extracting the tokens, and then constructing the data structure based on the parsed tokens. 

**My Implementation**
This implementation is written in java with maven structure.

Original ideas of Lexer and Parser from literature.

**The Challenge**
https://codingchallenges.fyi/challenges/challenge-json-parser/
Build Your Own JSON Parser ed in particolare lo step 0.

**Step Zero**

This is software engineering so we’re zero-indexed and for this step you’re going to set your environment up ready to begin developing and testing your solution.

I’ll leave you to setup your IDE / editor of choice and programming language of choice. After that you can download some simple test data for the JSON parser from my DropBox.

Prepariamo l'ambiente come progetto java maven che apriamo e gestiamo con VSCode

**Step 1**

In this step your goal is to parse a valid simple JSON object, specifically: ‘{}’ and an invalid JSON file and correctly report which is which. So you should build a very simple lexer and parser for this step.

Your program should report to the standard output stream a suitable message and exit with the code 0 for valid and 1 for invalid. It is conventional for CLI tools to return 0 for success and between 1 and 255 for an error and allows us to combined CLI tools to create more powerful programs. Check out write your own wc tool for more on combing simple cli tools.

You can test your code against the files in the folder tests/step1. Consider automating the tests so you can run them repeatedly as you progress through the challenge.
Parla di automatizzare con cli tool. Però voglio organizzare bene studiano JSON


**Step 2**
In this step your goal is to extend the parser to parse a simple JSON object containing string keys and string values, i.e.:
{"key": "value"} Note: this case comprises multiple key/value separated by comma.
You can test against the files in the folder tests/step2.

**Step3**
In this step your goal is to extend the parser to parse a JSON object containing string, numeric, boolean and null values i.e.
{
"key1": true,
"key2": false;
"key3": null,
"key4": "value",
"key5": 101
} 

**Step4**
In this step your goal is to extend the parser to parse a JSON object with object and array values, i.e.:

{
  "key": "value",
  "key-n": 101,
  "key-o": {},
  "key-l": []
}
You can test against the files in the folder tests/step4.

**Step5**
Step 5

In this step your goal is to add some of your own tests to ensure you’re confident that your parse can handle valid JSON and will fail with useful error messages on invalid JSON.

*Todo*
Once you’re confident your parser is done and well tested you can try running it against the test suite here: http://www.json.org/JSON_checker/test.zip
