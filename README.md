# Pradžia

Norint pasileisti testus reikia sukurti duomenų bazę vardu `employee-test`.
Visos lentelės (employees, salaries) bus automatiškai kiekvieną kartą sukuriamos ir
užpildomos testiniais duomenimis. Lentelių kūrimas ir duomenų užkrovimas aprašytas faile 
`schema.sql` kataloge `test/resources`  

### Del employee - salaries LAZY 

Spring JPA paprastai uždaro sesiją kaip suvykdomas kaoks tai JPA repositoriaus metodas ir
todėl jei turime LAZE asociaciją tai bandydami, tarkime, suskaičiuoti kiek salary įrašų
darbuotojas turi - gausime klaidą:
```
failed to lazily initialize a collection of role: lt.bit.java2.entities.Employee.salaries, could not initialize proxy - no Session
org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: lt.bit.java2.entities.Employee.salaries, could not initialize proxy - no Session
```
Klaidos galime išvengti keliais būdais:

1. pakeisti `Employee` klasėje `salaries` asociacijos tipą `FetchType.LAZY` į `FetchType.EAGER` - 
   kiekvienam `employee` visada trauks visus jo `salary` įrašus  
1. `application.properties` faile nurodyti parametrą 
   `spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true` -
   tada automatiškai bus generuojama reikiama SQL užklausa kai tik bandom pasiekti dar nepaimtus duomenis;
1. aprašyti jog  metodas vydomas tranzakcijoje (anotuoti `@Transactional) ir todėl sesija nuebus uždaroma - 
   nėra labai gerai, nes tada nepastebėsime savo neefektyvaus metodo;
1. naudoti `NamedEntityGraph` arba automatiškai generuojamus repositoriuje

Daugiau info apie šią problemą: 
- https://vladmihalcea.com/the-hibernate-enable_lazy_load_no_trans-anti-pattern/
- https://vladmihalcea.com/the-best-way-to-handle-the-lazyinitializationexception/ 
- https://spring.io/guides/gs/managing-transactions/

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.0.RELEASE/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.0.RELEASE/gradle-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.3.0.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Thymeleaf template engine](https://www.thymeleaf.org/)

### Using Thymeleaf template engine

* [Thymeleaf iterations](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#iteration)
* [Thymeleaf conditional statements](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#conditional-evaluation)
* [Thymeleaf urls](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#link-urls)


### Guides
The following guides illustrate how to use some features concretely:

* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

