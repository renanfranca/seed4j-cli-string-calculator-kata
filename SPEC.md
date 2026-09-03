# String Calculator Kata

Este kata deve ser feito incrementalmente, com testes primeiro e refatoração após cada teste. Não antecipe os próximos requisitos. Tente avançar o máximo possível em 30 minutos e repita o exercício do início em outras sessões.

## Passos

1. Crie um `StringCalculator` com uma operação `Add(numbers)` que retorna um número inteiro.
   - Comece pelos casos mais simples: uma string vazia, um número e dois números.
   - Uma string vazia retorna `0`.
   - A entrada pode conter zero, um ou dois números, como `""`, `"1"` ou `"1,2"`.
   - O resultado é a soma dos números informados.

2. Permita uma quantidade desconhecida de números.

3. Permita que quebras de linha também separem números.
   - `"1\n2,3"` retorna `6`.
   - `"1,\n"` é inválido, mas não precisa ser testado.

4. Permita um delimitador diferente.
   - A primeira linha opcional usa o formato `//[delimitador]\n[números]`.
   - `"//;\n1;2"` retorna `3`.
   - Os cenários existentes com vírgulas e quebras de linha continuam funcionando.

5. Ao receber números negativos, lance uma exceção com a mensagem `Negatives not allowed: ` seguida de todos os números negativos encontrados.
   - `"-1,2"` resulta em `Negatives not allowed: -1`.
   - `"2,-4,3,-5"` resulta em `Negatives not allowed: -4,-5`.

6. Ignore números maiores que `1000`.
   - `"1001,2"` retorna `2`.

7. Permita delimitadores de qualquer tamanho.
   - `"//[|||]\n1|||2|||3"` retorna `6`.

8. Permita múltiplos delimitadores.
   - `"//[|][%]\n1|2%3"` retorna `6`.

9. Permita múltiplos delimitadores de qualquer tamanho.

## Fonte

Adaptado do [String Calculator Kata](https://github.com/ardalis/kata-catalog/blob/main/katas/String%20Calculator.md) do kata-catalog.
