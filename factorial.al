def main
	CALL factorial
	RTRN
end

def factorial
	// setup
	LIT_INT 1
	STORE_INT product
	LIT_INT 1
	STORE_INT i

	// multiply product
	lbl loop
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
	JMP loop
	
	LOAD_INT product
	PRINT
	
	RTRN
end
