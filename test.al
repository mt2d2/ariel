// i = 0;
LIT_INT 0
STORE_INT i

// for (i = 0; i < 10; i++)
lbl loop
LOAD_INT i
PRINT

LIT_INT 1
ADD_INT
STORE_INT i

LIT_INT 10
LOAD_INT i

LE_INT 
JMP loop
