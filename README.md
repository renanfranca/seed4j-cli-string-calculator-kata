# Seed4J CLI Model Evaluation

This repository is a technical evaluation of how six Codex model/reasoning configurations use Seed4J CLI while implementing the same String Calculator kata. It compares each model's tool discovery, planning, module choices, reproducibility, behavioral result, tests, and design under a shared protocol.

Every task received the following prompt in Brazilian Portuguese:

**Original prompt (pt-BR, exactly as provided):**

> implement o kata utilizando o seed4j cli tool já instalado como apoio.

**English translation (for readers only):**

> Implement the kata using the already-installed Seed4J CLI tool as support.

The translation was not sent to the models and is not part of the experimental input.

## Principal findings

- All six implementations pass their native `./mvnw -q verify` build and the same JShell black-box suite for the nine required behaviors and the exact `public int add(String)`/negative-error contract.
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

## What the experiment demonstrates about Seed4J CLI

Across these six runs, the tasks used Seed4J CLI to discover available modules, inspect their help, preview changes with read-only plans, and apply a Java/Maven foundation. The resulting artifacts provide direct evidence of several capabilities exercised in this experiment:

- Seed4J resolved effective module order, including the Sol xhigh request that placed the wrapper before JaCoCo but committed JaCoCo first.
- Every run retained a Maven Wrapper, one `Apply module: ...` commit per successful module, and matching `.seed4j/modules` records with resolved parameters.
- Each pinned result used a conventional Maven layout and produced a build that passed `./mvnw -q verify`.

The workflow also varied by task. This evaluation fixed Seed4J CLI at v0.0.4 and runtime 2.2.0, with Java 25+, Node.js 22+, and npm as prerequisites. Only Sol medium, high, and xhigh selected the JaCoCo coverage-gate module. Terra added the Maven Wrapper reactively after discovering that global Maven was unavailable; the other runs included it in their initial applied module set.

These are observed outcomes, not a causal comparison: every run used Seed4J, so the experiment does not establish that Seed4J made implementation easier or faster, reduced token use, improved code quality, or outperformed another scaffolding approach. See [Cross-run Seed4J observations](MODEL_EVALUATION.md#cross-run-seed4j-observations) for the full evidence and interpretation boundary.

## Try Seed4J CLI Yourself

To reproduce this evaluation, use the pinned Seed4J CLI v0.0.4 and runtime 2.2.0 under the same controls. [Appendix C](MODEL_EVALUATION.md#appendix-c-reproduce-the-experiment) owns the exact prerequisites, installation commands, repository-local skill setup, branch procedure, prompt, and validation steps.

For general experimentation with the current release, follow the official [Seed4J CLI repository](https://github.com/seed4j/seed4j-cli). The official [OpenAI skills documentation](https://developers.openai.com/codex/skills/) explains repository-local skill discovery. Using a newer CLI, runtime, or bundled skill creates a new experiment rather than an exact reproduction of these scores.
