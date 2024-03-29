package esgi.al.cleancode.project.Super_Cards.bootstrap.server;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.junit.CacheMode.FOREVER;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static esgi.al.cleancode.project.Super_Cards.bootstrap.PackagesAndLayers.BOOTSTRAP_PACKAGE;
import static esgi.al.cleancode.project.Super_Cards.bootstrap.PackagesAndLayers.SERVER_PACKAGE;

@AnalyzeClasses(
    packages = "esgi.al.cleancode.project.Super_Cards.server",
    cacheMode = FOREVER,
    importOptions = {DoNotIncludeTests.class})
public class ServerDevelopmentRulesTest {

  @ArchTest
  public static final ArchRule SERVER_DEVELOPMENT_RULE =
      classes()
          .that()
          .resideInAPackage(SERVER_PACKAGE)
          .should()
          .onlyHaveDependentClassesThat()
          .resideInAnyPackage(SERVER_PACKAGE, BOOTSTRAP_PACKAGE)
          .andShould()
          .onlyBeAccessed()
          .byAnyPackage(BOOTSTRAP_PACKAGE, SERVER_PACKAGE);
}
