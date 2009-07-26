// factorial(N)
// {
//  	product = 1;
  
//  	for (i = 1; j <= N; i++ )
//   		product *= i;
  
// 		return product;
// }

// setup
LIT_INT 1
STORE_INT product
LIT_INT 1
STORE_INT i

// multiply product
LOAD_INT product
LOAD_INT i
MUL_INT
STORE_INT product

// increment i
LOAD_INT i
LIT_INT 1
ADD_INT
STORE_INT i

// compare
LIT_INT 5
LOAD_INT i
LEQ_INT
JMP -12

CALL version

INVOKE java.util.Date#toString
PRINT 

// print output
LOAD_INT product
PRINT
RTRN