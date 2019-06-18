//~~~~~~~~~~~~~~
// Trace -> Java

rule javaClass
match l : trace!Log
with  c : javacdo!ClassDeclaration
{
  compare
  {
    return l.source.split("\\.")[0] = c.name;
  }
}

//~~~~~~~~~~~~
// Java -> UML

rule component
match p : javacdo!Package
with  c : uml!Component
{
  compare
  {
    return p.name = c.name.toLowerCase();
  }
}

//~~~~~~~~~~~~~~~~~~~~
// UML -> Requirements

rule requirements
match c : uml!Component
with  r : req!Row
{
  compare
  {
    return c.name.toLowerCase().isSubstringOf(r.desc.toLowerCase());
  }
}
