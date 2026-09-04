# Seed4J CLI Model Evaluation

## Executive conclusion

Seed4J CLI was highly effective across all six runs. Every model discovered the active runtime, selected a minimal Java/Maven foundation, produced traceable module history, retained a project-local Maven Wrapper, and left an implementation that passes both its native build and a common black-box acceptance suite.

The strongest result in this experiment is Sol xhigh at 98/100. Sol low and Sol high tie at 96/100. At xhigh, Sol leads Luna (95) and Terra (91). These scores describe the six pinned artifacts under this protocol; they are not general-purpose model rankings.

## 1. Experimental protocol

### Fixed conditions

- Common implementation base: `38ebbcbfab95f5725b1c22b1d4701fb6222cab6b` (`string-calculator-kata`).
- Identical kata specification: the `SPEC.md` blob inherited from that common base.
- Exact shared prompt:

  > implement o kata utilizando o seed4j cli tool já instalado como apoio.

- Seed4J CLI: v0.0.4.
- Active runtime: Seed4J 2.2.0, standard mode.
- Same repository, host, filesystem, JDK family, and Maven dependency cache.
- Sequential rather than concurrent execution.
- Every evaluated result is pinned to a commit; no experiment branch was modified during evaluation.

The evaluation branch itself was created from `main` at `a7f92b3d3fb5c216da9afab72b4cb6b2db99f30d`, so it contains only the shared specification and this report—not any implementation.

### Changed variables

The intended variables were model and reasoning effort:

| Label | Branch | Pinned result | Implementation commit | Model | Effort |
| --- | --- | --- | --- | --- | --- |
| Sol low | `string-calculator-sol-low` | `34ce51f` | `f86d943` | `gpt-5.6-sol` | low |
| Sol medium | `string-calculator-sol-medium` | `bda5482` | `a682b5a` | `gpt-5.6-sol` | medium |
| Sol high | `string-calculator-sol-high` | `dfea557` | `efff912` | `gpt-5.6-sol` | high |
| Sol xhigh | `string-calculator-sol-xhigh` | `3d179c5` | `e22bea4` | `gpt-5.6-sol` | xhigh |
| Terra xhigh | `string-calculator-terra-xhigh` | `9001fe8` | `ff9727c` | `gpt-5.6-terra` | xhigh |
| Luna xhigh | `string-calculator-luna-xhigh` | `7ad4d48` | `31a1cb0` | `gpt-5.6-luna` | xhigh |

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
| Sol low | `20260903174822832-init.json`, `20260903174822979-maven-java.json`, `20260903174823015-maven-wrapper.json` |
| Sol medium | `20260903172604201-init.json`, `20260903172604361-maven-java.json`, `20260903172604397-jacoco-with-min-coverage-check.json`, `20260903172604427-maven-wrapper.json` |
| Sol high | `20260903160528226-init.json`, `20260903160528447-maven-java.json`, `20260903160528492-jacoco-with-min-coverage-check.json`, `20260903160528530-maven-wrapper.json` |
| Sol xhigh | `20260903150315685-init.json`, `20260903150315938-maven-java.json`, `20260903150315987-jacoco-with-min-coverage-check.json`, `20260903150316024-maven-wrapper.json` |
| Terra xhigh | `20260903183816670-init.json`, `20260903183816812-maven-java.json`, `20260903183853580-maven-wrapper.json` |
| Luna xhigh | `20260903185157204-init.json`, `20260903185157362-maven-java.json`, `20260903185157401-maven-wrapper.json` |

The Maven-facing files are identical in role across all runs: `pom.xml`, `mvnw`, `mvnw.cmd`, `.mvn/wrapper/maven-wrapper.jar`, and `.mvn/wrapper/maven-wrapper.properties`. The generated POMs use the conventional Maven build lifecycle and Java 25; the three JaCoCo runs enforce zero missed lines and zero missed branches during `verify`.

## 3. Final kata artifacts

### Source and test paths

| Result | Production source | Test source | Production lines | Test lines | Native tests |
| --- | --- | --- | ---: | ---: | ---: |
| Sol low | `src/main/java/com/renanfranca/stringcalculator/StringCalculator.java` | `src/test/java/com/renanfranca/stringcalculator/StringCalculatorTest.java` | 53 | 117 | 12 |
| Sol medium | `src/main/java/com/github/renanfranca/stringcalculator/StringCalculator.java` | `src/test/java/com/github/renanfranca/stringcalculator/StringCalculatorTest.java` | 42 | 108 | 11 |
| Sol high | `src/main/java/com/renanfranca/stringcalculator/StringCalculator.java` | `src/test/java/com/renanfranca/stringcalculator/StringCalculatorTest.java` | 56 | 108 | 11 |
| Sol xhigh | `src/main/java/com/renanfranca/stringcalculator/StringCalculator.java` | `src/test/java/com/renanfranca/stringcalculator/StringCalculatorTest.java` | 58 | 126 | 13 |
| Terra xhigh | `src/main/java/com/renanfranca/kata/StringCalculator.java` | `src/test/java/com/renanfranca/kata/StringCalculatorTest.java` | 45 | 117 | 12 |
| Luna xhigh | `src/main/java/com/renanfranca/stringcalculator/StringCalculator.java` | `src/test/java/com/renanfranca/stringcalculator/StringCalculatorTest.java` | 62 | 117 | 12 |

These counts are descriptive only. Neither test count nor production LOC receives points.

### Behavior delivered

All six expose the same minimal state-free API: a public `StringCalculator` class with instance method `public int add(String numbers)`. All implement the nine `SPEC.md` steps:

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
- Transcript formats differ substantially: prose-oriented Markdown, structured Markdown, raw JSONL-like audit output, and different truncation/omission policies. Audit presentation quality is therefore unweighted.
- The exact prompt and starting artifact were controlled, but generated package/base-name choices were left to each task and became observed outcomes.
- The black-box suite increases confidence in the shared public contract; it is not a proof over every possible malformed input or delimiter combination.
- This is one kata, one prompt, one host, and one run per model/effort cell. Results should not be generalized without repeated trials.

## Appendix A: contextual timing (unweighted)

The intervals below are derived from the first Seed4J module commit to the implementation commit. They begin after task startup/discovery and exclude the later transcript commit, so they are not full task durations.

| Result | First module commit | Implementation commit | Approximate interval |
| --- | --- | --- | ---: |
| Sol low | 14:48:22 | 14:55:15 | 6m 53s |
| Sol medium | 14:26:04 | 14:33:00 | 6m 56s |
| Sol high | 13:05:28 | 13:30:49 | 25m 21s |
| Sol xhigh | 12:03:15 | 12:53:44 | 50m 29s |
| Terra xhigh | 15:38:16 | 15:45:02 | 6m 46s |
| Luna xhigh | 15:51:57 | 16:01:38 | 9m 41s |

No timing points are awarded. Sequential execution, cache state, and commit timing boundaries prevent a fair speed ranking.

## Appendix B: transcript and audit artifacts (unweighted)

| Result | Artifact | Lines | Bytes |
| --- | --- | ---: | ---: |
| Sol low | `CONVERSATION_TRANSCRIPT.md` | 4,178 | 316,633 |
| Sol medium | `CONVERSATION_TRANSCRIPT.md` | 4,868 | 358,365 |
| Sol high | `CONVERSATION_TRANSCRIPT.md` | 4,966 | 316,132 |
| Sol xhigh | `AUDITORIA-CONVERSA.md` | 2,540 | 357,729 |
| Terra xhigh | `CONVERSATION_TRANSCRIPT.md` | 2,774 | 102,932 |
| Luna xhigh | `CONVERSATION_TRANSCRIPT.md` | 816 | 394,139 |

Line and byte counts illustrate format differences rather than completeness. The files record visible messages and tool activity while excluding private reasoning, internal instructions, encrypted content, and secrets. Because a transcript cannot fully contain its own creation and final commit without infinite self-reference, the final recording/commit operation may be absent or summarized. Transcript size and presentation receive no score.
