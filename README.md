# Java Code Static Analyzer

Course work for verification course.

## Usage
`java -jar static-analyzer.jar <project.dir or file>`

##Rules
- `AssignmentInIfStatementRule` - Avoid assignments in If condition; this can make code more complicated and harder to read.
- `ImportFromSamePackageRule` - There is no need to import a type that lives in the same package.
- `NamingConventionRule` - Class names, method names, method parameters, local variable names, field names must match appropriate patterns.
- `NullAssignmentRule` - Assigning a “null” to a variable (outside of its declaration) is usually bad form.
- `TopLevelPublicClassRule` - There there is exactly one top level public class in the file.

You can fine more detailed description of each rule in corresponding javadocs. 
see ```com.efimova.analyzer.rules``` package.


##Example output:
```
SomeJavaFile.java:	 No top-level public class detected.
SomeJavaFile.java:	 In declaration public static final String S = "S";: field "S" must match regex ^[A-Z]+_[A-Z]*$

BadFile.java:	 Class name "badFile" must match the pattern ([A-Z][a-z0-9]+)+
BadFile.java:	 In declaration @Getter private Context CTX;: field "CTX" must match regex ^[a-z][a-zA-Z0-9]*$
BadFile.java:	 In declaration public static final String s = "S";: field "s" must match regex ^[A-Z]+_[A-Z]*$
BadFile.java:	 Method SetRuleCheckers: "SetRuleCheckers" must match the pattern ^[a-z][a-zA-Z0-9]*$
BadFile.java:	 If statements "if((b = false) || (c = true))" contains assignment: "b = false"
BadFile.java:	 If statements "if((b = false) || (c = true))" contains assignment: "c = true"
BadFile.java:	 Import Statement "import com.efimova.analyzer.*;" in not necessary. Import of the same package.
BadFile.java:	 Null assignment found in: "// something... s = null;". Make sure you really need to assign NULL here: s = null
```
