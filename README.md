# CodeTeX
This program is for code highlighting and intending in plain TeX. It is console preprocessor for plain TeX. With this
program you can have simple syntax highlighting in plain TeX. CodeTeX can highlight only comments, keywords,
strings and indent code. You can set some variables for application and language, which you want to highlight. CodeTeX
uses OPmac library for default TeX macros (only colors). Make sure you have the newest OPmac library in your TeX
distribution. CodeTeX is written in Java 1.6, so you must have installed at least Java 1.6.

If you have not prerequisites for installing CodeTex, use builded jar file in CodeTeX folder.

One mistake, which i must repair. CodeTeX create log in folder from which you are executing the application.

Application version: 1.0

## Usage
Using this program is simple. You can start it using script or start application by java with jar file. CodeTeX can write
output to file or on console. This is defined by option -o. How to have correctly set settings and prepared input TeX
file see section *settings* and *Text needed in TeX file*.

Example how to use this application:
 
 `$CodeTeX -o output.tex test.tex`
 
 or
 
 `$CodeTeX test.tex`

## Text needed in TeX file
In input TeX file is needed some text. You must define begin of code block, which will be processed, with language name.
And define end of code block. Then will application look for {defined_language}_*.properties in settings folder. It can
look similar to this:

    ...
    Some text
    ...
    \codetexLangauge{java} \beginCodeBlock
        public class HelloWorld { 
           public static void main(String[] args) { 
              System.out.println("Hello, World");
           }
        }
    \endCodeBlock
    ...
    Another text
    ...

Then the tag \beginCodeBlock and tag \emdCodeBlock must be defined in settings.properties file. Another example is in
*test/test.tex* file.

**HINT:**
If you want to have option to compile input TeX file without this program add to begin program:

  \def\codetexLangauge #1 { }
  % And here must be definition of \beginCodeBlock and \endCodeBlock, which must be declare as verbatim block.

## Settings
CodeTeX has three settings and two for each language. If you want to see them, see **src/main/resources/settings**.
There is simple example how to have settings file filled. Every settings file contains simple description.

### codetex_macros
This file contains all macros which CodeTeX use for own purpose. You can add there some own macros which you want use.
But do not remove codetex* macros. CodeTeX will write all content of this file to begin of output file.

### settings.properties
It contains application variables, which you can set. The most important is block.start and block.end, which must be in
input TeX file, because for this strings application is looking.

### tex_macros.properties
In this file is mapping from unique string for macro to macro definition. In every macro you must define #T, because
application will change this with text.

### {language}_highlighting.properties
Here is mapping from language string to macro string. Language strings are: keywords, comment, string, class.method,
class.declaration, class.calling. Last three are not currently used.

### {language}_match.properties
This file is most important for language, because here is description of language string by language specific syntax.

## Building
You can build application by command `mvn package assembly:single` or use script install.sh. In first option you
will find application in target directory. In second option you can specify target directory or it will create own
directory CodeTeX.

### Prerequisites
If you want to build this program, you must have installed Maven and Java 1.6.

Copyright (c) 2015, Rostislav Novak. All rights reserved.
