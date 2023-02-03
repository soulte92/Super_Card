package esgi.al.cleancode.project.Super_Cards.bootstrap.bootstrap;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import esgi.al.cleancode.project.Super_Cards.bootstrap.PackagesAndLayers;

import static com.tngtech.archunit.junit.CacheMode.FOREVER;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(
    packages = "esgi.al.cleancode.project.Super_Cards.bootstrap",
    cacheMode = FOREVER,
    importOptions = {DoNotIncludeTests.class})
public class BootstrapDevelopmentRulesTest {

  @ArchTest
  public static final ArchRule BOOTSTRAP_DEVELOPMENT_RULE =
      classes()
          .that()
          .resideInAPackage(PackagesAndLayers.BOOTSTRAP_PACKAGE)
          .should()
          .onlyBeAccessed()
          .byClassesThat()
          .resideInAPackage(PackagesAndLayers.BOOTSTRAP_PACKAGE);
}
