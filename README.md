Objetivo: Determinaro número de falhas de páginas de diferentes algoritmos de substituição de páginas.

Dados de entradas serão logs de acesso àmemória disponíveis no diretório logs no Teams.

O  formato  do  trace  é  um  endereço  de  32  bits  representado  em  hexadecimal. Cada  linha  do arquivo  de  entrada  contém um endereço  de acesso àmemória.Os  endereços  com  0  à esquerda, tem o valor omitido, como no exemplo do endereço DAE3021, que seria equivalente a 0DAE3021.
Exemplo: 

B5E723DC

B5E723DC

67DAD61F

DAE3021.

O trace foi obtido em arquiteturas 32 bits.

Lembrando que o endereço é dividido em número da página | deslocamento. O tamanho da página determina quantos bits serão utilizados para o deslocamento. 

Ex: Se a página tem 4096 bytes de tamanho em uma arquitetura 32 bits, 12 bits são  necessários  para  o  deslocamento,  e  consequentemente  os  20  bits  mais  significativos representam o número da página.Lembrando que para a análise da falha de páginas, não énecessário analisar todas as referências à  memória,  apenas  a  página que será  acessada.  

Portanto,a  sequência  de  dois  acessos  ao endereço  B5E723DC,  será  analisado  apenas  uma  vez  pois  acessam  a  mesma  página. Nesse trabalho,  para  determinar  a  página  acessada  será  necessário  construir  o “reference string” considerando o tamanho da página, conforme apresentado em sala de aula.

O  trabalho  deverá  considerar  um  processador  com  tamanho  de  página  de  4096  bytes,  e implementar:

a )Geração da reference stringe apresentar o número de entradas da mesma, comparando o tamanho original do log com reference string gerada. 

Ex: o log tem originalmente 10 acessos  à  memória,  resultando  em  uma  reference  string  com  5  entradas. Portanto  a reference string em 0,5 do tamanho do log original.

b) A  partir  do reference stringobtido  no  item  (a),  obtero  número  de  falhas  de  página considerando diferentes tamanhos de frames livres (4, 8, 16, 32)nos algoritmos OPT e LRU.
