@str_fmt = constant [3 x i8] c"%d\00", align 1     ; Formato para enteros
@char_fmt = constant [3 x i8] c"%c\00", align 1     ; Formato para caracteres
@.newlineFormat = constant [2 x i8] c"\0A\00", align 1

; Declaración de variables globales de Codigo Fuente
@WELCOME_MESSAGE = constant [25 x i8] c"Bienvenido a MiniPascal!\00", align 1

@CHAR_EXAMPLE = constant [2 x i8] c"A\00", align 1

@lenght = common global i32 0, align 4

@i = common global i32 0, align 4

@j = common global i32 0, align 4

@x = common global i32 0, align 4

@y = common global i32 0, align 4

@cont = common global i32 0, align 4

@s = common global i8* null, align 8

define void @f(i32 %a, i32 %b) {
entry:
  ret void
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
  store i32 50, i32* @lenght
  store i32 10, i32* @x
  store i32 5, i32* @y
  store i32 1, i32* @cont
  %t0 = load i32, i32* @x
  store i32 %t0, i32* @i
  br label %L0
L0:
  %t1 = load i32, i32* @i
  %t2 = icmp sge i32 %t1, 1
  br i1 %t2, label %L1, label %L2
L1:
  %t3 = load i32, i32* @y
  store i32 %t3, i32* @j
  br label %L3
L3:
  %t4 = load i32, i32* @j
  %t5 = icmp sge i32 %t4, 1
  br i1 %t5, label %L4, label %L5
L4:
  %t6 = sub i32 %t4, 1
  store i32 %t6, i32* @j
  br label %L3
L5:
  %t7 = sub i32 %t1, 1
  store i32 %t7, i32* @i
  br label %L0
L2:
  store i32 1, i32* @cont
  %t8 = load i32, i32* @x
  store i32 1, i32* @i
  br label %L6
L6:
  %t9 = load i32, i32* @i
  %t10 = icmp sle i32 %t9, %t8
  br i1 %t10, label %L7, label %L8
L7:
  %t11 = load i32, i32* @y
  store i32 1, i32* @j
  br label %L9
L9:
  %t12 = load i32, i32* @j
  %t13 = icmp sle i32 %t12, %t11
  br i1 %t13, label %L10, label %L11
L10:
  %t14 = load i32, i32* @cont
  %t15 = add i32 %t14, 1
  store i32 %t15, i32* @cont
  %t16 = add i32 %t12, 1
  store i32 %t16, i32* @j
  br label %L9
L11:
  %t17 = add i32 %t9, 1
  store i32 %t17, i32* @i
  br label %L6
L8:
  store i32 1, i32* @cont
  br label %L12
L12:
  %t18 = load i32, i32* @cont
  %t19 = load i32, i32* @lenght
  %t20 = add i32 %t19, 1
  %t21 = icmp ne i32 %t18, %t20
  br i1 %t21, label %L13, label %L14
L13:
  %t22 = load i32, i32* @cont
  %respuesta22 = alloca [13 x i8], align 1
  store [13 x i8] c"'Contador= '\00", [13 x i8]* %respuesta22, align 1
  %respuesta_ptr22 = getelementptr inbounds [13 x i8], [13 x i8]* %respuesta22, i32 0, i32 0
  call void @write_string(i8* %respuesta_ptr22)
  call void @write_int(i32 %t22)
  %Integer24822 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.newlineFormat, i32 0, i32 0))
  %t23 = load i32, i32* @cont
  %t24 = add i32 %t23, 1
  store i32 %t24, i32* @cont
  br label %L12
L14:
  %resultado248 = alloca [27 x i8], align 1
  store [27 x i8] c"'Gracias por usar Pascal!'\00", [27 x i8]* %resultado248, align 1
  %resultado248_ptr = getelementptr inbounds [27 x i8], [27 x i8]* %resultado248, i32 0, i32 0
  call void @write_string(i8* %resultado248_ptr)
  %_101 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.newlineFormat, i32 0, i32 0))
  ret i32 0
}
