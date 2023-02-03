package esgi.al.cleancode.project.Super_Cards.bootstrap;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.junit.CacheMode.FOREVER;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(
    packages = "esgi.al.cleancode.project",
    cacheMode = FOREVER,
    importOptions = {DoNotIncludeTests.class})
public class LayersDevelopmentRulesTest {

  @ArchTest
  public static final ArchRule LAYERS_DEVELOPMENT_RULE =
      layeredArchitecture()
          .layer(PackagesAndLayers.DOMAIN_LAYER)
          .definedBy(PackagesAndLayers.DOMAIN_PACKAGE)// utiliser un objet class.getBasePackage() plutôt que hardcoder les packages
          .layer(PackagesAndLayers.BOOTSTRAP_LAYER)
          .definedBy(PackagesAndLayers.BOOTSTRAP_PACKAGE)
          .layer(PackagesAndLayers.CLIENT_LAYER)
          .definedBy(PackagesAndLayers.CLIENT_PACKAGE)
          .layer(PackagesAndLayers.REST_ADAPTER_LAYER)
          .definedBy(PackagesAndLayers.REST_ADAPTER_PACKAGE)
          .layer(PackagesAndLayers.SERVER_LAYER)
          .definedBy(PackagesAndLayers.SERVER_PACKAGE)
          .layer(PackagesAndLayers.POSTGRES_ADAPTER_LAYER)
          .definedBy(PackagesAndLayers.POSTGRES_ADAPTER_PACKAGE)
          .whereLayer(PackagesAndLayers.DOMAIN_LAYER)
          .mayOnlyBeAccessedByLayers(PackagesAndLayers.BOOTSTRAP_LAYER, PackagesAndLayers.CLIENT_LAYER, PackagesAndLayers.SERVER_LAYER)
          .whereLayer(PackagesAndLayers.BOOTSTRAP_LAYER)
          .mayNotBeAccessedByAnyLayer()
          .whereLayer(PackagesAndLayers.CLIENT_LAYER)
          .mayOnlyBeAccessedByLayers(PackagesAndLayers.BOOTSTRAP_LAYER)
          .whereLayer(PackagesAndLayers.REST_ADAPTER_LAYER)
          .mayOnlyBeAccessedByLayers(PackagesAndLayers.BOOTSTRAP_LAYER)
          .whereLayer(PackagesAndLayers.SERVER_LAYER)
          .mayOnlyBeAccessedByLayers(PackagesAndLayers.BOOTSTRAP_LAYER)
          .whereLayer(PackagesAndLayers.POSTGRES_ADAPTER_LAYER)
          .mayOnlyBeAccessedByLayers(PackagesAndLayers.BOOTSTRAP_LAYER)
          .because("That's the main and most important hexagonal architecture rule !");
}
