package com.dxc.eproc.rateanalysis;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.dxc.eproc.rateanalysis");

        noClasses()
            .that()
            .resideInAnyPackage("com.dxc.eproc.rateanalysis.service..")
            .or()
            .resideInAnyPackage("com.dxc.eproc.rateanalysis.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.dxc.eproc.rateanalysis.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
