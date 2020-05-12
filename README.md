# SelfAutoRestDoc

Create an automatic SpringBoot REST documentation to markdown with Spoon (http://spoon.gforge.inria.fr/).

It's still in alpha.

See pom.xml file for more details.

![Java CI with Maven](https://github.com/hdsdi3g/selfautorestdoc/workflows/Java%20CI%20with%20Maven/badge.svg)

## Setup

Add in your pom file:

```
<dependency>
    <groupId>tv.hd3g.commons</groupId>
    <artifactId>selfautorestdoc</artifactId>
    <version>(last current version)</version>
</dependency>
```

And start (or copy and start) from your Spring Boot project scripts/make-rest-doc.sh. You will needs maven to run it.

## Contributing / debugging

For run the tests, you just needs Maven.

Versioning: just use [SemVer](https://semver.org/).

## Author and License

This project is writer by [hdsdi3g](https://github.com/hdsdi3g) and licensed under the LGPL License; see the LICENCE.TXT file for details.
