@str_fmt = constant [3 x i8] c"%d\00", align 1     ; Formato para enteros
@char_fmt = constant [3 x i8] c"%c\00", align 1     ; Formato para caracteres
@n = common global i32 0, align 4

@resultado = common global i32 0, align 4

@WELCOME_MESSAGE = constant [25 x i8] c"Bienvenido a MiniPascal!\00", align 1

@CHAR_EXAMPLE = constant [2 x i8] c"A\00", align 1

define void @write_int(i32 %num) {
    call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @str_fmt, i32 0, i32 0), i32 %num)
    ret void
}
define void @write_char(i8 %char) {
    call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @char_fmt, i32 0, i32 0), i8 %char)
    ret void
}
define void @write_string(i8* %str) {
    call i32 @puts(i8* %str)
    ret void
}
define void @writeln(i8* %str) {
    ; Imprimir la cadena
    call i32 @puts(i8* %str)
    
    ; Imprimir un salto de línea
    call void @write_char(i8 10)  ; 10 es el código ASCII para el salto de línea '\n'
    
    ret void
}
declare i32 @printf(i8*, ...) #1
declare i32 @atoi(i8*)
declare i32 @sprintf(i8*, i8*, ...)
declare i32 @puts(i8*)
declare void @exit(i32)
; Main
define i32 @main() {
  store i32 5, i32* @n
  store i32 1, i32* @resultado
  %t0 = load i32, i32* @n
  %respuesta0 = alloca [6 x i8], align 1
  store [6 x i8] c"'N: '\00", [6 x i8]* %respuesta0, align 1
  %respuesta_ptr0 = getelementptr inbounds [6 x i8], [6 x i8]* %respuesta0, i32 0, i32 0
  call void @write_string(i8* %respuesta_ptr0)
  call void @write_int(i32 %t0)
  br label %L0
L0:
  %t1 = load i32, i32* @n
  %t2 = icmp sgt i32 %t1, 0
  br i1 %t2, label %L1, label %L2
L1:
  %t3 = load i32, i32* @n
  %t4 = srem i32 %t3, 2
  %t5 = icmp eq i32 %t4, 0
  br i1 %t5, label %L3, label %L4
L3:
  %t6 = load i32, i32* @resultado
  %t7 = mul i32 %t6, 2
  store i32 %t7, i32* @resultado
  br label %L5
L4:
  %t8 = load i32, i32* @resultado
  %t9 = add i32 %t8, 1
  store i32 %t9, i32* @resultado
  br label %L5
L5:
  %t10 = load i32, i32* @n
  %t11 = sub i32 %t10, 1
  store i32 %t11, i32* @n
  br label %L0
L2:
  %t12 = load i32, i32* @resultado
  %respuesta12 = alloca [20 x i8], align 1
  store [20 x i8] c"'El resultado es: '\00", [20 x i8]* %respuesta12, align 1
  %respuesta_ptr12 = getelementptr inbounds [20 x i8], [20 x i8]* %respuesta12, i32 0, i32 0
  call void @write_string(i8* %respuesta_ptr12)
  call void @write_int(i32 %t12)
    ret i32 0
}
