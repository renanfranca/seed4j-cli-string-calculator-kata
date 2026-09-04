# Seed4J CLI Model Evaluation

This repository compares six isolated implementations of the String Calculator kata. The experiment asks how effectively different Codex models and reasoning efforts use Seed4J CLI to create a reproducible Java/Maven foundation and then complete the same behavior-driven exercise.

Every task received the following prompt in Brazilian Portuguese:

**Original prompt (pt-BR, exactly as provided):**

> implement o kata utilizando o seed4j cli tool já instalado como apoio.

**English translation (for readers only):**

> Implement the kata using the already-installed Seed4J CLI tool as support.

The translation was not sent to the models and is not part of the experimental input.

## Try Seed4J CLI Yourself

[Seed4J CLI](https://github.com/seed4j/seed4j-cli) lets an agent discover and apply only the modules a project needs. Its read-only plans expose dependencies, execution order, and resolved parameters before mutation; successful applications can also leave a Maven Wrapper, focused Git commits, and auditable `.seed4j/modules` history. All six runs in this experiment produced a reproducible build through that workflow.

The CLI requires Java 25 or newer, Node.js 22 or newer, and npm. To reproduce the toolchain evaluated here, install the pinned package and confirm both versions:

```bash
npm install -g seed4j-cli@0.0.4
seed4j --version
```

Expected version information:

```text
Seed4J CLI v0.0.4
Seed4J version: 2.2.0
Runtime mode: standard
```

From the root of any project, install the bundled agent skill locally:

```bash
seed4j skill install
```

This creates `.agents/skills/seed4j-cli`. Codex discovers repository-local skills automatically; restart Codex if a newly installed skill does not appear, then select `seed4j-cli` in the Skills UI or invoke `$seed4j-cli` explicitly where skill mentions are supported. See the official [OpenAI skills documentation](https://developers.openai.com/codex/skills/) for skill structure and discovery locations.

For general experimentation with the latest CLI instead of reproducing these scores, omit the version pin:

```bash
npm install -g seed4j-cli
```

A newer CLI, Seed4J runtime, or bundled skill defines a new experiment and should not be compared directly with the pinned results below. The complete branch and validation procedure is in [Reproduce the experiment](MODEL_EVALUATION.md#appendix-c-reproduce-the-experiment).

## Controlled experiment

All implementations started from commit [`38ebbcb`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/38ebbcbfab95f5725b1c22b1d4701fb6222cab6b), used the same [`SPEC.md`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/blob/38ebbcbfab95f5725b1c22b1d4701fb6222cab6b/SPEC.md), Seed4J CLI v0.0.4 with runtime Seed4J 2.2.0, the same host, and sequential execution. The only intended variables were the Codex model and reasoning effort.

| Branch | Pinned result | Model | Effort |
| --- | --- | --- | --- |
| [`string-calculator-sol-low`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/tree/string-calculator-sol-low) | [`34ce51f`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/34ce51f99cd924c890ce6c24d3bdd90d6f710f6e) | `gpt-5.6-sol` | low |
| [`string-calculator-sol-medium`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/tree/string-calculator-sol-medium) | [`bda5482`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/bda5482344c1c12531f16d74de6aeb4c50ce732e) | `gpt-5.6-sol` | medium |
| [`string-calculator-sol-high`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/tree/string-calculator-sol-high) | [`dfea557`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/dfea557a4cca180ae016654ebafb6664dbf4a098) | `gpt-5.6-sol` | high |
| [`string-calculator-sol-xhigh`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/tree/string-calculator-sol-xhigh) | [`3d179c5`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/3d179c56b288f0fcbc0c62ee94b5af3152887136) | `gpt-5.6-sol` | xhigh |
| [`string-calculator-terra-xhigh`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/tree/string-calculator-terra-xhigh) | [`9001fe8`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/9001fe863565408ac3c9622b3b9e7e3edb7786f6) | `gpt-5.6-terra` | xhigh |
| [`string-calculator-luna-xhigh`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/tree/string-calculator-luna-xhigh) | [`7ad4d48`](https://github.com/renanfranca/seed4j-cli-string-calculator-kata/commit/7ad4d48b311ff664c2f8e4b012151513cad15916) | `gpt-5.6-luna` | xhigh |

## Principal findings

- Seed4J was effective in every run: all six produced a conventional Maven project, a checked-in Maven Wrapper, one commit and one `.seed4j/modules` record per applied module, and a build that passes `./mvnw -q verify`.
- All six implementations pass the same JShell black-box suite for the nine required behaviors and the exact `public int add(String)`/negative-error contract.
- Sol xhigh leads this experiment with 98/100. Sol low and Sol high tie at 96/100, showing that higher reasoning effort did not improve results monotonically.
- At xhigh, Sol scores 98, Luna 95, and Terra 91. The differences come from optional coverage enforcement, proactive module planning, native edge tests, and design robustness—not from the required kata behavior, which all three satisfy.
- The unrequired overlapping-delimiter probe passes on Sol low, Sol xhigh, and Luna xhigh, and fails on Sol medium, Sol high, and Terra xhigh.

| Rank | Result | Score |
| ---: | --- | ---: |
| 1 | Sol xhigh | 98 |
| 2= | Sol low | 96 |
| 2= | Sol high | 96 |
| 4 | Luna xhigh | 95 |
| 5 | Sol medium | 94 |
| 6 | Terra xhigh | 91 |

Test count, production LOC, transcript size, and elapsed time are reported as context and receive no direct points. See [MODEL_EVALUATION.md](MODEL_EVALUATION.md) for the complete protocol, exact Seed4J commands, validation cases, evidence, rubric, deductions, and limitations.
