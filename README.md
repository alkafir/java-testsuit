# Testsuit

## Introduction
Testsuit is a test framework for the Java platform, intended for usage with the J8SE and compatible specifications.
It's meant to be easy to implement and quick to learn and expand.
Its source code has been widely documented with the javadoc specification for maximum understanding.


## Test case definition
To define a test case is pretty easy.
You just write a java class, add some methods, mark the test methods as tests and you're done.
Examples are provided in the `samples/` directory.
For finer behaviour tuning, annotations are provided.

If you need to setup/cleanup the test environment, you can add to your test case class the following (both optional) definitions:

    public void setup() {
      ...
    }
    
    public void cleanup() {
      ...
    }

## Test case execution
To run a test case you have to first compile it.
You do it the same way you do with other java files, including the testsuit jar file in your classpath.

Ex.:

    javac -cp lib/testsuit-1.0.jar tests/MyTest.java


After you compile it, you can run the test case by running the `wisedevil.test.EntryPoint` class, providing the test case class file as a command line argument.

Ex.:

    java -cp lib/testsuit-1.0.jar:build/ wisedevil.test.EntryPoint tests/MyTest.class


### Executing in Apache Ant
For running the tests in Apache Ant, you are provided with an Ant Task (`wisedevil.ant.taskdefs.RunTests`).

First, add the task definition to your project like this:

    <taskdef name="runtests"
    	classname="wisedevil.ant.taskdefs.RunTests"
    	classpath="lib/testsuit-1.0.jar"
    	onerror="ignore"
    />

Then, after tests compilation, you can run them with:

    <runtests classpath="${build.dir}">
    	<fileset dir="${test.dir}">
    		<include name="*.class" />
    	</fileset>
    </runtests>

The source code for the ant task, in `ant-tasks/` provides documentation.

### Embedding
If you give a glance to the `wisedevil.test.EntryPoint` class' source file, you can see that it's really easy to embed testsuit in your existing application, as it would require just a few lines of code.

## Test runtime tools
### Annotations
The `wisedevil.test.annotation` package provides annotations.
### Classes
The `wisedevil.test.result` package provides test result managers for exporting test results (for use with annotations).
### Methods
The `wisedevil.test.Assert` class provides static methods for the test methods.

As you can see in the documentation, there is no `assert()` method, since you can use the Java `assert` keyword to perform assertions.
 
## Further information
Please referer to the javadoc output for further information.

## Bugs and suggestions
Feel free to report bugs and submit suggestions via the issue tracker at the project's page. Any help is appreciated.

## Links
- [Project page @github.com](https://github.com/alkafir/testsuit "Project")
- [API @github.io](http://alkafir.github.io/java-testsuit/ "API")
