# Seed4J CLI Model Evaluation

This repository compares six isolated implementations of the String Calculator kata. The experiment asks how effectively different Codex models and reasoning efforts use Seed4J CLI to create a reproducible Java/Maven foundation and then complete the same behavior-driven exercise.

Every task received the exact prompt:

> implement o kata utilizando o seed4j cli tool já instalado como apoio.

## Controlled experiment

All implementations started from commit `38ebbcb`, used the same `SPEC.md`, Seed4J CLI v0.0.4 with runtime Seed4J 2.2.0, the same host, and sequential execution. The only intended variables were the Codex model and reasoning effort.

| Branch | Pinned result | Model | Effort |
| --- | --- | --- | --- |
| `string-calculator-sol-low` | `34ce51f` | `gpt-5.6-sol` | low |
| `string-calculator-sol-medium` | `bda5482` | `gpt-5.6-sol` | medium |
| `string-calculator-sol-high` | `dfea557` | `gpt-5.6-sol` | high |
| `string-calculator-sol-xhigh` | `3d179c5` | `gpt-5.6-sol` | xhigh |
| `string-calculator-terra-xhigh` | `9001fe8` | `gpt-5.6-terra` | xhigh |
| `string-calculator-luna-xhigh` | `7ad4d48` | `gpt-5.6-luna` | xhigh |

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
