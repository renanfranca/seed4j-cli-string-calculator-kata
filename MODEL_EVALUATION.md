# Seed4J CLI Model Evaluation

## Executive conclusion

This report is a technical evaluation of how six Codex model/reasoning configurations used Seed4J CLI under one shared kata protocol. Within these six pinned runs, every model discovered the active runtime, selected a Java/Maven foundation, produced traceable module history, retained a project-local Maven Wrapper, and left an implementation that passes both its native build and a common black-box acceptance suite.

The strongest result in this experiment is Sol xhigh at 98/100. Sol low and Sol high tie at 96/100. At xhigh, Sol leads Luna (95) and Terra (91). These scores describe the six pinned artifacts and how their models used Seed4J under this protocol; they are not general-purpose model rankings or evidence of Seed4J's causal effect.

## 1. Experimental protocol

### Fixed conditions

- Common implementation base: [`38ebbcb`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/38ebbcbfab95f5725b1c22b1d4701fb6222cab6b) ([`string-calculator-kata`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/tree/string-calculator-kata)).
- Identical kata specification: the [`SPEC.md`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/38ebbcbfab95f5725b1c22b1d4701fb6222cab6b/SPEC.md) blob inherited from that common base.
- Shared prompt language: Brazilian Portuguese (`pt-BR`).
- Original prompt, exactly as provided:

  > implement o kata utilizando o seed4j cli tool já instalado como apoio.

- English translation, for readers only:

  > Implement the kata using the already-installed Seed4J CLI tool as support.

  The translation was not sent to the models and is not part of the experimental input.

- Seed4J CLI: v0.0.4.
- Active runtime: Seed4J 2.2.0, standard mode.
- Same repository, host, filesystem, JDK family, and Maven dependency cache.
- Sequential rather than concurrent execution.
- Every evaluated result is pinned to a commit; no experiment branch was modified during evaluation.

The evaluation branch itself was created from [`main`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/tree/main) at [`a7f92b3`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/a7f92b3d3fb5c216da9afab72b4cb6b2db99f30d), so it contains only the shared specification and this report—not any implementation.

### Changed variables

The intended variables were model and reasoning effort:

| Label | Branch | Pinned result | Implementation commit | Model | Effort |
| --- | --- | --- | --- | --- | --- |
| Sol low | [`string-calculator-sol-low`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/tree/string-calculator-sol-low) | [`34ce51f`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/34ce51f99cd924c890ce6c24d3bdd90d6f710f6e) | [`f86d943`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/f86d943dcb4b5b08677502c4891c17233c7fc034) | `gpt-5.6-sol` | low |
| Sol medium | [`string-calculator-sol-medium`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/tree/string-calculator-sol-medium) | [`bda5482`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/bda5482344c1c12531f16d74de6aeb4c50ce732e) | [`a682b5a`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/a682b5a2e9a7d02391a18d3c01555f732fb5d5f0) | `gpt-5.6-sol` | medium |
| Sol high | [`string-calculator-sol-high`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/tree/string-calculator-sol-high) | [`dfea557`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/dfea557a4cca180ae016654ebafb6664dbf4a098) | [`efff912`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/efff912c45831e4801e6d73083a675afe74367f8) | `gpt-5.6-sol` | high |
| Sol xhigh | [`string-calculator-sol-xhigh`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/tree/string-calculator-sol-xhigh) | [`3d179c5`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/3d179c56b288f0fcbc0c62ee94b5af3152887136) | [`e22bea4`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/e22bea4e216db808ae7682a63d857f2cce3d8c0b) | `gpt-5.6-sol` | xhigh |
| Terra xhigh | [`string-calculator-terra-xhigh`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/tree/string-calculator-terra-xhigh) | [`9001fe8`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/9001fe863565408ac3c9622b3b9e7e3edb7786f6) | [`ff9727c`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/ff9727c027f2981be3ba014628ae512eb209c7b7) | `gpt-5.6-terra` | xhigh |
| Luna xhigh | [`string-calculator-luna-xhigh`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/tree/string-calculator-luna-xhigh) | [`7ad4d48`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/7ad4d48b311ff664c2f8e4b012151513cad15916) | [`31a1cb0`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/31a1cb02f7ca6bb9bd251b7702c8c20cad04e8c8) | `gpt-5.6-luna` | xhigh |

The two primary comparisons are therefore:

1. Sol low → medium → high → xhigh, isolating reasoning effort within one model.
2. Sol → Terra → Luna at xhigh, comparing models at the same effort.

## 2. Seed4J CLI usage

### Discovery and help commands

Commands below preserve the spelling and option style recorded in each transcript. Semicolon- or `&&`-joined commands were issued in a single shell call but are shown as Seed4J-only lines for readability.

#### Sol low

```bash
seed4j --version; seed4j --help; seed4j list
seed4j apply init --help; seed4j apply maven-java --help; seed4j apply jqwik --help
seed4j apply-set --help
```

This run inspected an unused `jqwik` candidate but did not inspect `maven-wrapper --help` before selecting the wrapper.

#### Sol medium

```bash
seed4j --version; seed4j --help; seed4j list
seed4j apply init --help; seed4j apply maven-java --help; seed4j apply maven-wrapper --help; seed4j apply jacoco-with-min-coverage-check --help; seed4j apply checkstyle --help
seed4j apply-set --help
```

#### Sol high

```bash
seed4j --version && seed4j --help
seed4j list
seed4j apply init --help
seed4j apply maven-java --help
seed4j apply jacoco-with-min-coverage-check --help
seed4j apply maven-wrapper --help
seed4j apply-set --help
```

#### Sol xhigh

```bash
seed4j --version && seed4j --help
seed4j list
seed4j apply-set --help
seed4j apply init --help
seed4j apply maven-java --help
seed4j apply maven-wrapper --help
seed4j apply jacoco-with-min-coverage-check --help
```

#### Terra xhigh

```bash
seed4j --version && seed4j list && seed4j --help
seed4j apply maven-java --help
seed4j apply init --help
seed4j apply-set --help
seed4j apply maven-wrapper --help
```

#### Luna xhigh

```bash
seed4j --version; seed4j list; seed4j --help
seed4j apply init --help; seed4j apply maven-java --help; seed4j apply java-base --help; seed4j apply spring-boot --help; seed4j apply-set --help
seed4j apply maven-wrapper --help
```

Luna inspected `java-base` and `spring-boot` as candidates, then correctly kept them out of the minimal kata scaffold.

### Exact plan and apply commands

#### Sol low

```bash
seed4j apply-set init maven-java maven-wrapper --plan --project-path . --project-name 'String Calculator Kata' --base-name stringCalculator --package-name com.renanfranca.stringcalculator --node-package-manager npm --end-of-line lf --indent-size 2
seed4j apply-set init maven-java maven-wrapper --project-path . --project-name 'String Calculator Kata' --base-name stringCalculator --package-name com.renanfranca.stringcalculator --node-package-manager npm --end-of-line lf --indent-size 2
```

#### Sol medium

```bash
seed4j apply-set init maven-java jacoco-with-min-coverage-check maven-wrapper --plan --project-name='String Calculator Kata' --base-name=stringCalculator --package-name=com.github.renanfranca.stringcalculator --node-package-manager=npm --end-of-line=lf --indent-size=2
seed4j apply-set init maven-java jacoco-with-min-coverage-check maven-wrapper --project-name='String Calculator Kata' --base-name=stringCalculator --package-name=com.github.renanfranca.stringcalculator --node-package-manager=npm --end-of-line=lf --indent-size=2
```

#### Sol high

```bash
seed4j apply-set init maven-java jacoco-with-min-coverage-check maven-wrapper --plan --project-name='String Calculator Kata' --base-name=stringCalculator --package-name=com.renanfranca.stringcalculator --node-package-manager=npm --end-of-line=lf --indent-size=2 --project-path=.
seed4j apply-set init maven-java jacoco-with-min-coverage-check maven-wrapper --project-name='String Calculator Kata' --base-name=stringCalculator --package-name=com.renanfranca.stringcalculator --node-package-manager=npm --end-of-line=lf --indent-size=2 --project-path=.
```

#### Sol xhigh

```bash
seed4j apply-set init maven-java maven-wrapper jacoco-with-min-coverage-check --plan --project-path . --project-name 'String Calculator Kata' --base-name stringCalculator --package-name com.renanfranca.stringcalculator --node-package-manager npm --end-of-line lf --indent-size 2
seed4j apply-set init maven-java maven-wrapper jacoco-with-min-coverage-check --project-path . --project-name 'String Calculator Kata' --base-name stringCalculator --package-name com.renanfranca.stringcalculator --node-package-manager npm --end-of-line lf --indent-size 2
```

The requested order placed the wrapper before JaCoCo; Seed4J resolved and committed `init` → `maven-java` → `jacoco-with-min-coverage-check` → `maven-wrapper`.

#### Terra xhigh

Terra first planned the two foundational modules, applied them, and added the wrapper later after detecting that global Maven was unavailable:

```bash
seed4j apply maven-java --plan --base-name=StringCalculator --package-name=com.renanfranca.kata --project-name='String Calculator Kata'
seed4j apply init --plan --base-name=StringCalculator --project-name='String Calculator Kata' --node-package-manager=npm --end-of-line=lf --indent-size=2
seed4j apply-set init maven-java --plan --base-name=StringCalculator --package-name=com.renanfranca.kata --project-name='String Calculator Kata' --node-package-manager=npm --end-of-line=lf --indent-size=2
seed4j apply-set init maven-java --base-name=StringCalculator --package-name=com.renanfranca.kata --project-name='String Calculator Kata' --node-package-manager=npm --end-of-line=lf --indent-size=2
seed4j apply maven-wrapper --plan
seed4j apply maven-wrapper
```

#### Luna xhigh

Luna also used an individual dependency plan before the final set plan:

```bash
seed4j apply maven-java --plan --base-name=StringCalculator --project-name='String Calculator Kata' --package-name=com.renanfranca.stringcalculator --project-path=.
seed4j apply-set init maven-java maven-wrapper --plan --base-name=StringCalculator --project-name='String Calculator Kata' --package-name=com.renanfranca.stringcalculator --node-package-manager=npm --project-path=. --end-of-line=lf --indent-size=2
seed4j apply-set init maven-java maven-wrapper --base-name=StringCalculator --project-name='String Calculator Kata' --package-name=com.renanfranca.stringcalculator --node-package-manager=npm --project-path=. --end-of-line=lf --indent-size=2
```

### Modules, parameters, and reproducible history

| Result | Effective module order | `baseName` | `packageName` | JaCoCo gate |
| --- | --- | --- | --- | --- |
| Sol low | `init`, `maven-java`, `maven-wrapper` | `stringCalculator` | `com.renanfranca.stringcalculator` | No |
| Sol medium | `init`, `maven-java`, `jacoco-with-min-coverage-check`, `maven-wrapper` | `stringCalculator` | `com.github.renanfranca.stringcalculator` | Yes |
| Sol high | `init`, `maven-java`, `jacoco-with-min-coverage-check`, `maven-wrapper` | `stringCalculator` | `com.renanfranca.stringcalculator` | Yes |
| Sol xhigh | `init`, `maven-java`, `jacoco-with-min-coverage-check`, `maven-wrapper` | `stringCalculator` | `com.renanfranca.stringcalculator` | Yes |
| Terra xhigh | `init`, `maven-java`, then `maven-wrapper` | `StringCalculator` | `com.renanfranca.kata` | No |
| Luna xhigh | `init`, `maven-java`, `maven-wrapper` | `StringCalculator` | `com.renanfranca.stringcalculator` | No |

All runs explicitly supplied `projectName='String Calculator Kata'`, `nodePackageManager=npm`, `endOfLine=lf`, and `indentSize=2` when applying `init`. Sol medium and Terra relied on the current directory instead of spelling `--project-path`; their recorded project path was still the repository root.

Every successful module produced one `Apply module: ...` commit and one record under `.seed4j/modules`:

| Result | Exact `.seed4j/modules` records |
| --- | --- |
| Sol low | [`20260903174822832-init.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/34ce51f99cd924c890ce6c24d3bdd90d6f710f6e/.seed4j/modules/20260903174822832-init.json), [`20260903174822979-maven-java.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/34ce51f99cd924c890ce6c24d3bdd90d6f710f6e/.seed4j/modules/20260903174822979-maven-java.json), [`20260903174823015-maven-wrapper.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/34ce51f99cd924c890ce6c24d3bdd90d6f710f6e/.seed4j/modules/20260903174823015-maven-wrapper.json) |
| Sol medium | [`20260903172604201-init.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/bda5482344c1c12531f16d74de6aeb4c50ce732e/.seed4j/modules/20260903172604201-init.json), [`20260903172604361-maven-java.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/bda5482344c1c12531f16d74de6aeb4c50ce732e/.seed4j/modules/20260903172604361-maven-java.json), [`20260903172604397-jacoco-with-min-coverage-check.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/bda5482344c1c12531f16d74de6aeb4c50ce732e/.seed4j/modules/20260903172604397-jacoco-with-min-coverage-check.json), [`20260903172604427-maven-wrapper.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/bda5482344c1c12531f16d74de6aeb4c50ce732e/.seed4j/modules/20260903172604427-maven-wrapper.json) |
| Sol high | [`20260903160528226-init.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/dfea557a4cca180ae016654ebafb6664dbf4a098/.seed4j/modules/20260903160528226-init.json), [`20260903160528447-maven-java.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/dfea557a4cca180ae016654ebafb6664dbf4a098/.seed4j/modules/20260903160528447-maven-java.json), [`20260903160528492-jacoco-with-min-coverage-check.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/dfea557a4cca180ae016654ebafb6664dbf4a098/.seed4j/modules/20260903160528492-jacoco-with-min-coverage-check.json), [`20260903160528530-maven-wrapper.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/dfea557a4cca180ae016654ebafb6664dbf4a098/.seed4j/modules/20260903160528530-maven-wrapper.json) |
| Sol xhigh | [`20260903150315685-init.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/3d179c56b288f0fcbc0c62ee94b5af3152887136/.seed4j/modules/20260903150315685-init.json), [`20260903150315938-maven-java.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/3d179c56b288f0fcbc0c62ee94b5af3152887136/.seed4j/modules/20260903150315938-maven-java.json), [`20260903150315987-jacoco-with-min-coverage-check.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/3d179c56b288f0fcbc0c62ee94b5af3152887136/.seed4j/modules/20260903150315987-jacoco-with-min-coverage-check.json), [`20260903150316024-maven-wrapper.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/3d179c56b288f0fcbc0c62ee94b5af3152887136/.seed4j/modules/20260903150316024-maven-wrapper.json) |
| Terra xhigh | [`20260903183816670-init.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/9001fe863565408ac3c9622b3b9e7e3edb7786f6/.seed4j/modules/20260903183816670-init.json), [`20260903183816812-maven-java.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/9001fe863565408ac3c9622b3b9e7e3edb7786f6/.seed4j/modules/20260903183816812-maven-java.json), [`20260903183853580-maven-wrapper.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/9001fe863565408ac3c9622b3b9e7e3edb7786f6/.seed4j/modules/20260903183853580-maven-wrapper.json) |
| Luna xhigh | [`20260903185157204-init.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/7ad4d48b311ff664c2f8e4b012151513cad15916/.seed4j/modules/20260903185157204-init.json), [`20260903185157362-maven-java.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/7ad4d48b311ff664c2f8e4b012151513cad15916/.seed4j/modules/20260903185157362-maven-java.json), [`20260903185157401-maven-wrapper.json`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/7ad4d48b311ff664c2f8e4b012151513cad15916/.seed4j/modules/20260903185157401-maven-wrapper.json) |

The Maven-facing files are identical in role across all runs. The table below links every build artifact to the corresponding pinned result; “wrapper runtime” contains both `maven-wrapper.jar` and `maven-wrapper.properties`. The generated POMs use the conventional Maven build lifecycle and Java 25; the three JaCoCo runs enforce zero missed lines and zero missed branches during `verify`.

| Result | POM | Wrapper scripts | Wrapper runtime |
| --- | --- | --- | --- |
| Sol low | [`pom.xml`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/34ce51f99cd924c890ce6c24d3bdd90d6f710f6e/pom.xml) | [`mvnw`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/34ce51f99cd924c890ce6c24d3bdd90d6f710f6e/mvnw), [`mvnw.cmd`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/34ce51f99cd924c890ce6c24d3bdd90d6f710f6e/mvnw.cmd) | [`maven-wrapper.jar`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/34ce51f99cd924c890ce6c24d3bdd90d6f710f6e/.mvn/wrapper/maven-wrapper.jar), [`maven-wrapper.properties`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/34ce51f99cd924c890ce6c24d3bdd90d6f710f6e/.mvn/wrapper/maven-wrapper.properties) |
| Sol medium | [`pom.xml`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/bda5482344c1c12531f16d74de6aeb4c50ce732e/pom.xml) | [`mvnw`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/bda5482344c1c12531f16d74de6aeb4c50ce732e/mvnw), [`mvnw.cmd`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/bda5482344c1c12531f16d74de6aeb4c50ce732e/mvnw.cmd) | [`maven-wrapper.jar`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/bda5482344c1c12531f16d74de6aeb4c50ce732e/.mvn/wrapper/maven-wrapper.jar), [`maven-wrapper.properties`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/bda5482344c1c12531f16d74de6aeb4c50ce732e/.mvn/wrapper/maven-wrapper.properties) |
| Sol high | [`pom.xml`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/dfea557a4cca180ae016654ebafb6664dbf4a098/pom.xml) | [`mvnw`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/dfea557a4cca180ae016654ebafb6664dbf4a098/mvnw), [`mvnw.cmd`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/dfea557a4cca180ae016654ebafb6664dbf4a098/mvnw.cmd) | [`maven-wrapper.jar`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/dfea557a4cca180ae016654ebafb6664dbf4a098/.mvn/wrapper/maven-wrapper.jar), [`maven-wrapper.properties`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/dfea557a4cca180ae016654ebafb6664dbf4a098/.mvn/wrapper/maven-wrapper.properties) |
| Sol xhigh | [`pom.xml`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/3d179c56b288f0fcbc0c62ee94b5af3152887136/pom.xml) | [`mvnw`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/3d179c56b288f0fcbc0c62ee94b5af3152887136/mvnw), [`mvnw.cmd`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/3d179c56b288f0fcbc0c62ee94b5af3152887136/mvnw.cmd) | [`maven-wrapper.jar`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/3d179c56b288f0fcbc0c62ee94b5af3152887136/.mvn/wrapper/maven-wrapper.jar), [`maven-wrapper.properties`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/3d179c56b288f0fcbc0c62ee94b5af3152887136/.mvn/wrapper/maven-wrapper.properties) |
| Terra xhigh | [`pom.xml`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/9001fe863565408ac3c9622b3b9e7e3edb7786f6/pom.xml) | [`mvnw`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/9001fe863565408ac3c9622b3b9e7e3edb7786f6/mvnw), [`mvnw.cmd`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/9001fe863565408ac3c9622b3b9e7e3edb7786f6/mvnw.cmd) | [`maven-wrapper.jar`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/9001fe863565408ac3c9622b3b9e7e3edb7786f6/.mvn/wrapper/maven-wrapper.jar), [`maven-wrapper.properties`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/9001fe863565408ac3c9622b3b9e7e3edb7786f6/.mvn/wrapper/maven-wrapper.properties) |
| Luna xhigh | [`pom.xml`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/7ad4d48b311ff664c2f8e4b012151513cad15916/pom.xml) | [`mvnw`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/7ad4d48b311ff664c2f8e4b012151513cad15916/mvnw), [`mvnw.cmd`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/7ad4d48b311ff664c2f8e4b012151513cad15916/mvnw.cmd) | [`maven-wrapper.jar`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/7ad4d48b311ff664c2f8e4b012151513cad15916/.mvn/wrapper/maven-wrapper.jar), [`maven-wrapper.properties`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/7ad4d48b311ff664c2f8e4b012151513cad15916/.mvn/wrapper/maven-wrapper.properties) |

### Cross-run Seed4J observations

This section summarizes what the six model runs demonstrably did with Seed4J CLI; the preceding commands, pinned module records, and build artifacts remain the evidence for each observation.

- Every model inspected the CLI runtime, module catalog, and help, then generated at least one read-only plan before applying modules.
- Seed4J resolved dependencies into an effective module order. The clearest example is Sol xhigh: its request listed `maven-wrapper` before `jacoco-with-min-coverage-check`, while the resulting commits placed JaCoCo before the wrapper.
- Every successful module application produced an `Apply module: ...` commit and a matching `.seed4j/modules` record containing the resolved parameters.
- All six results retained the Maven Wrapper and conventional Maven source layout, and all six pinned builds passed `./mvnw -q verify`.

The model-driven choices were not uniform. Only Sol medium, Sol high, and Sol xhigh selected `jacoco-with-min-coverage-check`; Sol low, Terra xhigh, and Luna xhigh did not enforce a coverage gate. Terra initially omitted `maven-wrapper` and added it only after global Maven was unavailable, whereas the other runs included it in their initial applied module set. Discovery breadth, package names, and base-name capitalization also varied by task.

These observations apply to Seed4J CLI v0.0.4 with runtime 2.2.0 and the Java 25+, Node.js 22+, and npm prerequisites used here. Because Seed4J was present in every run, they describe capabilities exercised by the evaluated models rather than a causal advantage over another workflow.

## 3. Final kata artifacts

### Source and test paths

| Result | Production source | Test source | Production lines | Test lines | Native tests |
| --- | --- | --- | ---: | ---: | ---: |
| Sol low | [`src/main/java/com/renanfranca/stringcalculator/StringCalculator.java`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/34ce51f99cd924c890ce6c24d3bdd90d6f710f6e/src/main/java/com/renanfranca/stringcalculator/StringCalculator.java) | [`src/test/java/com/renanfranca/stringcalculator/StringCalculatorTest.java`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/34ce51f99cd924c890ce6c24d3bdd90d6f710f6e/src/test/java/com/renanfranca/stringcalculator/StringCalculatorTest.java) | 53 | 117 | 12 |
| Sol medium | [`src/main/java/com/github/renanfranca/stringcalculator/StringCalculator.java`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/bda5482344c1c12531f16d74de6aeb4c50ce732e/src/main/java/com/github/renanfranca/stringcalculator/StringCalculator.java) | [`src/test/java/com/github/renanfranca/stringcalculator/StringCalculatorTest.java`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/bda5482344c1c12531f16d74de6aeb4c50ce732e/src/test/java/com/github/renanfranca/stringcalculator/StringCalculatorTest.java) | 42 | 108 | 11 |
| Sol high | [`src/main/java/com/renanfranca/stringcalculator/StringCalculator.java`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/dfea557a4cca180ae016654ebafb6664dbf4a098/src/main/java/com/renanfranca/stringcalculator/StringCalculator.java) | [`src/test/java/com/renanfranca/stringcalculator/StringCalculatorTest.java`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/dfea557a4cca180ae016654ebafb6664dbf4a098/src/test/java/com/renanfranca/stringcalculator/StringCalculatorTest.java) | 56 | 108 | 11 |
| Sol xhigh | [`src/main/java/com/renanfranca/stringcalculator/StringCalculator.java`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/3d179c56b288f0fcbc0c62ee94b5af3152887136/src/main/java/com/renanfranca/stringcalculator/StringCalculator.java) | [`src/test/java/com/renanfranca/stringcalculator/StringCalculatorTest.java`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/3d179c56b288f0fcbc0c62ee94b5af3152887136/src/test/java/com/renanfranca/stringcalculator/StringCalculatorTest.java) | 58 | 126 | 13 |
| Terra xhigh | [`src/main/java/com/renanfranca/kata/StringCalculator.java`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/9001fe863565408ac3c9622b3b9e7e3edb7786f6/src/main/java/com/renanfranca/kata/StringCalculator.java) | [`src/test/java/com/renanfranca/kata/StringCalculatorTest.java`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/9001fe863565408ac3c9622b3b9e7e3edb7786f6/src/test/java/com/renanfranca/kata/StringCalculatorTest.java) | 45 | 117 | 12 |
| Luna xhigh | [`src/main/java/com/renanfranca/stringcalculator/StringCalculator.java`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/7ad4d48b311ff664c2f8e4b012151513cad15916/src/main/java/com/renanfranca/stringcalculator/StringCalculator.java) | [`src/test/java/com/renanfranca/stringcalculator/StringCalculatorTest.java`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/7ad4d48b311ff664c2f8e4b012151513cad15916/src/test/java/com/renanfranca/stringcalculator/StringCalculatorTest.java) | 62 | 117 | 12 |

These counts are descriptive only. Neither test count nor production LOC receives points.

### Behavior delivered

All six expose the same minimal state-free API: a public `StringCalculator` class with instance method `public int add(String numbers)`. All implement the nine [`SPEC.md`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/38ebbcbfab95f5725b1c22b1d4701fb6222cab6b/SPEC.md) steps:

1. Empty, one-number, and two-number inputs.
2. An arbitrary number count.
3. Newline separators.
4. A custom delimiter header.
5. Rejection of all negatives with their values in input order.
6. Ignoring values above 1000 while retaining 1000.
7. A delimiter of arbitrary length.
8. Multiple delimiters.
9. Multiple delimiters of arbitrary length.

### Independent validation

Each pinned commit was exported into a new disposable directory without checking out or editing its branch:

```bash
case_dir="$(mktemp -d)"
git archive <pinned-commit> | tar -x -C "$case_dir"
(cd "$case_dir" && ./mvnw -q verify)
```

After compilation, the common black-box suite used the generic SDKMAN Java path rather than a version-specific directory:

```bash
/home/renanfranca/.sdkman/candidates/java/current/bin/jshell --class-path "$case_dir/target/classes"
```

For each package, JShell imported its `StringCalculator`, instantiated it, called `add`, checked integer results, and checked exception type and exact message. The common cases were:

| Case | Input | Expected |
| --- | --- | --- |
| Empty | `""` | `0` |
| Single | `"7"` | `7` |
| Two | `"1,2"` | `3` |
| Many | `"1,2,3,4"` | `10` |
| Newline | `"1\n2,3"` | `6` |
| Custom delimiter | `"//;\n1;2"` | `3` |
| Above 1000 | `"1001,2"` | `2` |
| Boundary | `"1000,1001,2"` | `1002` |
| Long delimiter | `"//[|||]\n1|||2|||3"` | `6` |
| Multiple delimiters | `"//[|][%]\n1|2%3"` | `6` |
| Multiple long delimiters | `"//[***][%%%]\n1***2%%%3"` | `6` |
| One negative | `"-1,2"` | `IllegalArgumentException`: `Negatives not allowed: -1` |
| Multiple negatives | `"2,-4,3,-5"` | `IllegalArgumentException`: `Negatives not allowed: -4,-5` |

### Validation results

| Result | Native `./mvnw -q verify` | Native tests | Common JShell core | Overlap bonus `//[*][**]\n1**2*3` |
| --- | --- | ---: | --- | --- |
| Sol low | Pass | 12 | Pass | Pass (`6`) |
| Sol medium | Pass | 11 | Pass | Fail |
| Sol high | Pass | 11 | Pass | Fail |
| Sol xhigh | Pass | 13 | Pass | Pass (`6`) |
| Terra xhigh | Pass | 12 | Pass | Fail |
| Luna xhigh | Pass | 12 | Pass | Pass (`6`) |

The overlap probe is deliberately separate. `SPEC.md` requires multiple arbitrary-length delimiters but does not require one delimiter to be a prefix of another. It therefore earns robustness credit but does not reduce behavioral-correctness points.

### Design observations

- **Sol low:** clear `parseInput` and `rejectNegatives` helpers plus a small parsed-input record. Delimiters are regex-quoted and sorted longest-first, so overlap succeeds.
- **Sol medium:** the smallest implementation, but parsing, negative validation, filtering, and summing remain concentrated in `add`. Delimiters are quoted but not ordered longest-first.
- **Sol high:** the clearest responsibility split (`parse`, `parseInput`, `delimiterPattern`, and `rejectNegatives`) with a parsed-input record. Its delimiter alternatives are not ordered longest-first.
- **Sol xhigh:** readable and robust, with longest-first delimiter ordering and an explicit overlap regression test, but most parsing and calculation responsibility remains in one method.
- **Terra xhigh:** compact stream-based implementation with most responsibilities in `add`; quoted delimiter alternatives retain declaration order, so overlap fails.
- **Luna xhigh:** extracts delimiter-pattern construction and sorts delimiters longest-first. Its main loop still combines parsing, validation, and summing, but the implementation passes the overlap probe.

All six keep the public API minimal, introduce no shared mutable state, and use conventional Maven source/test layout.

## 4. Scorecard

### Rubric

| Category | Points | Allocation |
| --- | ---: | --- |
| Seed4J effectiveness | 35 | discovery/help 5; preflight and plan 8; module choice/order 8; explicit parameters 7; reproducible history and wrapper 7 |
| Behavioral correctness | 30 | nine required steps × 3 = 27; exact public/negative-error contract 3 |
| Test quality | 20 | native `verify` 6; requirement coverage 8; negative/delimiter edge coverage 3; enforced coverage gate 3 |
| Design and reproducibility | 15 | separation and clarity 6; delimiter robustness 4; minimal public state/API 3; conventional Maven layout 2 |

Edge-test points are one each for a native single-negative exact-message test, a native multiple-negative exact-order test, and a native overlapping-delimiter regression test. Delimiter-robustness points are 2 for safely quoting and handling the required long/multiple forms plus 2 for passing the overlap probe. This makes every deduction auditable and avoids rewarding raw test count or LOC.

### Primary view 1: Sol reasoning effort

| Sol effort | Seed4J /35 | Behavior /30 | Tests /20 | Design /15 | Total /100 |
| --- | ---: | ---: | ---: | ---: | ---: |
| low | 34 | 30 | 17 | 15 | **96** |
| medium | 35 | 30 | 18 | 11 | **94** |
| high | 35 | 30 | 18 | 13 | **96** |
| xhigh | 35 | 30 | 20 | 13 | **98** |

Reasoning effort was not monotonic. Xhigh produced the strongest overall artifact because it combined full Seed4J discipline, a JaCoCo gate, complete negative edge tests, and an overlap regression. Low tied high despite lacking JaCoCo because low's implementation and native tests handled the overlap edge and its design separated responsibilities well. High had stronger coverage enforcement and decomposition, but no single-negative or overlap regression and failed the external overlap probe. Medium was fully correct and concise, but had the weakest separation among the Sol variants and the same two native edge-test omissions as high.

### Primary view 2: models at xhigh

| xhigh model | Seed4J /35 | Behavior /30 | Tests /20 | Design /15 | Total /100 |
| --- | ---: | ---: | ---: | ---: | ---: |
| Sol | 35 | 30 | 20 | 13 | **98** |
| Terra | 34 | 30 | 16 | 11 | **91** |
| Luna | 35 | 30 | 16 | 14 | **95** |

All three are behaviorally equivalent on the required contract. Sol separates itself with the JaCoCo zero-miss gate and the native overlap regression. Luna omits the gate and overlap regression but its delimiter design still passes the bonus probe. Terra also omits the gate, adds the Maven Wrapper reactively rather than in its initial module set, concentrates responsibilities in `add`, and fails the overlap probe.

### Aggregate ranking

| Rank | Result | Score |
| ---: | --- | ---: |
| 1 | Sol xhigh | 98 |
| 2= | Sol low | 96 |
| 2= | Sol high | 96 |
| 4 | Luna xhigh | 95 |
| 5 | Sol medium | 94 |
| 6 | Terra xhigh | 91 |

The Sol low/Sol high tie is retained: the rubric does not introduce an unweighted tie-breaker.

### Deduction ledger

Every score starts from 100; this table lists every deduction. Categories or subcriteria not listed received full credit.

| Result | Deduction | Branch evidence |
| --- | ---: | --- |
| Sol low | −1 Seed4J discovery/help | Selected `maven-wrapper` without recording `seed4j apply maven-wrapper --help`; inspected unused `jqwik` instead. |
| Sol low | −3 enforced coverage gate | No `jacoco-with-min-coverage-check` module or equivalent enforced gate. |
| Sol medium | −2 negative/delimiter edge tests | Native suite has the multiple-negative exact-order test, but no separate single-negative test and no overlap regression. |
| Sol medium | −2 separation/clarity | Parsing, negative validation, threshold filtering, and summing remain concentrated in `add`. |
| Sol medium | −2 delimiter robustness | Required regex quoting works, but declaration-order alternation fails the overlap probe. |
| Sol high | −2 negative/delimiter edge tests | Native suite has the multiple-negative exact-order test, but no separate single-negative test and no overlap regression. |
| Sol high | −2 delimiter robustness | Required regex quoting works, but declaration-order alternation fails the overlap probe. |
| Sol xhigh | −2 separation/clarity | Most parsing, validation, filtering, and summing responsibility remains in `add` despite readable local structure. |
| Terra xhigh | −1 module choice/order | Initial module set omitted `maven-wrapper`; it was planned and applied only after global Maven was found unavailable. |
| Terra xhigh | −1 negative/delimiter edge tests | Native suite covers both negative-message cases but has no overlap regression. |
| Terra xhigh | −3 enforced coverage gate | No `jacoco-with-min-coverage-check` module or equivalent enforced gate. |
| Terra xhigh | −2 separation/clarity | Most parsing, validation, filtering, and summing responsibility remains in `add`. |
| Terra xhigh | −2 delimiter robustness | Required regex quoting works, but declaration-order alternation fails the overlap probe. |
| Luna xhigh | −1 negative/delimiter edge tests | Native suite covers both negative-message cases but has no overlap regression. |
| Luna xhigh | −3 enforced coverage gate | No `jacoco-with-min-coverage-check` module or equivalent enforced gate. |
| Luna xhigh | −1 separation/clarity | Delimiter construction is extracted, while parsing, validation, filtering, and summing remain combined in the main loop. |

## 5. Limitations

- Runs were sequential. Based on commit timestamps, the order was Sol xhigh, Sol high, Sol medium, Sol low, Terra xhigh, then Luna xhigh.
- Maven dependencies and Seed4J runtime data could be warm for later runs. Cache warming makes elapsed-time comparison especially weak.
- Commit timestamps measure repository events, not end-to-end model latency. Tool scheduling, retries, transcript generation, and user orchestration are not normalized.
- Token usage and monetary cost are unavailable, so no cost-effectiveness claim or score is possible.
- Every task used Seed4J CLI; there was no control group implementing the kata without it. The results therefore do not demonstrate that Seed4J made implementation easier or faster, reduced token usage, improved code quality, or outperformed another scaffolding or manual approach.
- Transcript formats differ substantially: prose-oriented Markdown, structured Markdown, raw JSONL-like audit output, and different truncation/omission policies. Audit presentation quality is therefore unweighted.
- The exact prompt and starting artifact were controlled, but generated package/base-name choices were left to each task and became observed outcomes.
- The black-box suite increases confidence in the shared public contract; it is not a proof over every possible malformed input or delimiter combination.
- This is one kata, one prompt, one host, and one run per model/effort cell. Results should not be generalized without repeated trials.

## Appendix A: contextual timing (unweighted)

The intervals below are derived from the first Seed4J module commit to the implementation commit. They begin after task startup/discovery and exclude the later transcript commit, so they are not full task durations.

| Result | First module commit | Implementation commit | Approximate interval |
| --- | --- | --- | ---: |
| Sol low | [`e3ac20f` · 14:48:22](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/e3ac20fa0d3516ba8eda9f73622462729c641e92) | [`f86d943` · 14:55:15](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/f86d943dcb4b5b08677502c4891c17233c7fc034) | 6m 53s |
| Sol medium | [`d15774d` · 14:26:04](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/d15774df4329c64ba97009e294a0c37d7e0df249) | [`a682b5a` · 14:33:00](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/a682b5a2e9a7d02391a18d3c01555f732fb5d5f0) | 6m 56s |
| Sol high | [`1205a19` · 13:05:28](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/1205a19b49527e09959e7d0ec33f046ce8bdf01e) | [`efff912` · 13:30:49](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/efff912c45831e4801e6d73083a675afe74367f8) | 25m 21s |
| Sol xhigh | [`bc2937f` · 12:03:15](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/bc2937fbd28b1af9e8d225e3d1aa04e139ce7d88) | [`e22bea4` · 12:53:44](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/e22bea4e216db808ae7682a63d857f2cce3d8c0b) | 50m 29s |
| Terra xhigh | [`65352d6` · 15:38:16](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/65352d678971082c114dbd1b0f10db4bfd1d33d5) | [`ff9727c` · 15:45:02](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/ff9727c027f2981be3ba014628ae512eb209c7b7) | 6m 46s |
| Luna xhigh | [`5fb3d7f` · 15:51:57](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/5fb3d7f3709a414058c1a1d274084f40a57a95bc) | [`31a1cb0` · 16:01:38](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/31a1cb02f7ca6bb9bd251b7702c8c20cad04e8c8) | 9m 41s |

No timing points are awarded. Sequential execution, cache state, and commit timing boundaries prevent a fair speed ranking.

## Appendix B: transcript and audit artifacts (unweighted)

| Result | Artifact | Lines | Bytes |
| --- | --- | ---: | ---: |
| Sol low | [`CONVERSATION_TRANSCRIPT.md`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/34ce51f99cd924c890ce6c24d3bdd90d6f710f6e/CONVERSATION_TRANSCRIPT.md) | 4,178 | 316,633 |
| Sol medium | [`CONVERSATION_TRANSCRIPT.md`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/bda5482344c1c12531f16d74de6aeb4c50ce732e/CONVERSATION_TRANSCRIPT.md) | 4,868 | 358,365 |
| Sol high | [`CONVERSATION_TRANSCRIPT.md`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/dfea557a4cca180ae016654ebafb6664dbf4a098/CONVERSATION_TRANSCRIPT.md) | 4,966 | 316,132 |
| Sol xhigh | [`AUDITORIA-CONVERSA.md`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/3d179c56b288f0fcbc0c62ee94b5af3152887136/AUDITORIA-CONVERSA.md) | 2,540 | 357,729 |
| Terra xhigh | [`CONVERSATION_TRANSCRIPT.md`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/9001fe863565408ac3c9622b3b9e7e3edb7786f6/CONVERSATION_TRANSCRIPT.md) | 2,774 | 102,932 |
| Luna xhigh | [`CONVERSATION_TRANSCRIPT.md`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/7ad4d48b311ff664c2f8e4b012151513cad15916/CONVERSATION_TRANSCRIPT.md) | 816 | 394,139 |

Line and byte counts illustrate format differences rather than completeness. The files record visible messages and tool activity while excluding private reasoning, internal instructions, encrypted content, and secrets. Because a transcript cannot fully contain its own creation and final commit without infinite self-reference, the final recording/commit operation may be absent or summarized. Transcript size and presentation receive no score.

## Appendix C: reproduce the experiment

### 1. Install the evaluated CLI

Seed4J CLI requires Java 25 or newer, Node.js 22 or newer, and npm. Install the exact npm package used in this experiment:

```bash
java -version
node --version
npm --version
npm install -g seed4j-cli@0.0.4
seed4j --version
```

The final command must report:

```text
Seed4J CLI v0.0.4
Seed4J version: 2.2.0
Runtime mode: standard
```

The official [Seed4J CLI repository](https://github.com/seed4j/seed4j-cli) also documents an unpinned `npm install -g seed4j-cli` installation. Use that form to explore the latest release, not to reproduce this evaluation: a different CLI, runtime, or bundled skill creates a different experimental condition.

### 2. Prepare an isolated branch

Clone this repository and create every new run directly from the controlled base:

```bash
git clone https://github.com/renanfranca/seed4j-cli-string-calculator-kata.git
cd seed4j-cli-string-calculator-kata
git switch --create string-calculator-my-run origin/string-calculator-kata
```

Choose a unique branch name for each model/effort pair. Do not start from `main`, this evaluation branch, or another implementation branch.

Commit `38ebbcb` on `origin/string-calculator-kata` already contains the repository-local skill used by all evaluated tasks:

```text
.agents/skills/seed4j-cli/
├── SKILL.md
└── references/
    ├── applying-modules.md
    └── module-set-planning.md
```

The three checked-in files are byte-for-byte identical to those installed by the pinned CLI's `seed4j skill install` command. For a different project that does not already contain them, run this from that project's root:

```bash
seed4j skill install
```

The command installs the bundled skill at `.agents/skills/seed4j-cli`; `seed4j skill install --global` is a different, user-level choice and was not used in this experiment. Codex scans `.agents/skills` from the working directory to the repository root, as described by the official [OpenAI skills documentation](https://developers.openai.com/codex/skills/).

### 3. Start a fresh Codex task

Start or restart Codex in the repository root after checking out the new branch. Confirm that `seed4j-cli` appears in the Skills UI or `/skills` listing, choose the model and reasoning effort being evaluated, and send exactly this prompt:

> implement o kata utilizando o seed4j cli tool já instalado como apoio.

Do not add the English translation to the task. Do not show the task another implementation branch, its source, tests, transcript, or score before it finishes. Use a fresh branch and a fresh Codex task for every run. If several runs will be compared, execute them sequentially and record their order because later runs may benefit from warm Maven or Seed4J caches.

### 4. Validate and record the result

After the task finishes, run the native build and inspect the generated audit trail:

```bash
./mvnw -q verify
git log --oneline --reverse origin/string-calculator-kata..HEAD
find .seed4j/modules -maxdepth 1 -type f -print | sort
```

Confirm that the Seed4J module commits precede the kata implementation commit and that the project retains its Maven Wrapper. For a direct comparison with this report, repeat the public API cases under [Independent validation](#independent-validation) with JShell, including the separate overlapping-delimiter bonus.

Record the pinned result commit, model, reasoning effort, CLI/runtime versions, native test result, common acceptance result, execution order, and any cache differences. Without those controls, the result is useful as a Seed4J exercise but not as a new cell in this evaluation.
