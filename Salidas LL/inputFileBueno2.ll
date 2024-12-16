@str_fmt = constant [3 x i8] c"%d\00", align 1     ; Formato para enteros
@char_fmt = constant [3 x i8] c"%c\00", align 1     ; Formato para caracteres
@.newlineFormat = constant [2 x i8] c"\0A\00", align 1

; Declaración de variables globales de Codigo Fuente
@i = common global i32 0, align 4

@x = common global i32 0, align 4

@total = common global i32 0, align 4

define i32 @Sumar(i32 %a, i32 %b) {
entry:
  %t0 = add i32 %a, %b

  ret i32 %t0

}

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
  store i32 10, i32* @x
  store i32 0, i32* @total
  %t0 = load i32, i32* @x
  store i32 1, i32* @i
  br label %L0
L0:
  %t1 = load i32, i32* @i
  %t2 = icmp sle i32 %t1, %t0
  br i1 %t2, label %L1, label %L2
L1:
  %t3 = load i32, i32* @total
  %t4 = load i32, i32* @i
  %t5 = call i32 @Sumar(i32 %t3, i32 %t4)
  store i32 %t5, i32* @total
  %t6 = add i32 %t1, 1
  store i32 %t6, i32* @i
  br label %L0
L2:
  %t7 = load i32, i32* @total
  %respuesta7 = alloca [16 x i8], align 1
  store [16 x i8] c"'El total es: '\00", [16 x i8]* %respuesta7, align 1
  %respuesta_ptr7 = getelementptr inbounds [16 x i8], [16 x i8]* %respuesta7, i32 0, i32 0
  call void @write_string(i8* %respuesta_ptr7)
  call void @write_int(i32 %t7)
  %integer = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.newlineFormat, i32 0, i32 0))
  ret i32 0
}
