@str_fmt = constant [3 x i8] c"%d\00", align 1     ; Formato para enteros
@char_fmt = constant [3 x i8] c"%c\00", align 1     ; Formato para caracteres
@.newlineFormat = constant [2 x i8] c"\0A\00", align 1

; Declaración de variables globales de Codigo Fuente
@lenght = common global i32 0, align 4

@i = common global i32 0, align 4

@j = common global i32 0, align 4

@x = common global i32 0, align 4

@y = common global i32 0, align 4

@cont = common global i32 0, align 4

@arreglo = global [50 x i32] zeroinitializer, align 16

@arreglo_bidi = global [10 x [5 x i32]] zeroinitializer, align 16

@s = common global i8* null, align 8

@bool = common global i1 0, align 1

define i32 @f() {
entry:
  ret i32 3

}

define void @f2(i32 %a, i32 %b) {
entry:
  %resultado37 = alloca [7 x i8], align 1
  store [7 x i8] c"'Hola'\00", [7 x i8]* %resultado37, align 1
  %resultado37_ptr = getelementptr inbounds [7 x i8], [7 x i8]* %resultado37, i32 0, i32 0
  call void @write_string(i8* %resultado37_ptr)
  %_43 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.newlineFormat, i32 0, i32 0))

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
  store i1 true, i1* @bool
  %t0 = load i32, i32* @x
  store i32 %t0, i32* @i
  br label %L0
L0:
  %t1 = load i32, i32* @i
  %t2 = icmp sge i32 %t1, 1
  br i1 %t2, label %L1, label %L2
L1:
  %t3 = load i32, i32* @i
  %respuesta3 = alloca [12 x i8], align 1
  store [12 x i8] c"'Estoy en '\00", [12 x i8]* %respuesta3, align 1
  %respuesta_ptr3 = getelementptr inbounds [12 x i8], [12 x i8]* %respuesta3, i32 0, i32 0
  call void @write_string(i8* %respuesta_ptr3)
  call void @write_int(i32 %t3)
  %Integer1983 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.newlineFormat, i32 0, i32 0))
  %t4 = load i32, i32* @y
  store i32 %t4, i32* @j
  br label %L3
L3:
  %t5 = load i32, i32* @j
  %t6 = icmp sge i32 %t5, 1
  br i1 %t6, label %L4, label %L5
L4:
  %t7 = load i32, i32* @j
  %respuesta7 = alloca [12 x i8], align 1
  store [12 x i8] c"'Estoy en '\00", [12 x i8]* %respuesta7, align 1
  %respuesta_ptr7 = getelementptr inbounds [12 x i8], [12 x i8]* %respuesta7, i32 0, i32 0
  call void @write_string(i8* %respuesta_ptr7)
  call void @write_int(i32 %t7)
  %Integer1757 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.newlineFormat, i32 0, i32 0))
  %t8 = sub i32 %t5, 1
  store i32 %t8, i32* @j
  br label %L3
L5:
  %t9 = sub i32 %t1, 1
  store i32 %t9, i32* @i
  br label %L0
L2:
  store i32 1, i32* @cont
  %t10 = load i32, i32* @x
  store i32 1, i32* @i
  br label %L6
L6:
  %t11 = load i32, i32* @i
  %t12 = icmp sle i32 %t11, %t10
  br i1 %t12, label %L7, label %L8
L7:
  %t13 = load i32, i32* @i
  %respuesta13 = alloca [12 x i8], align 1
  store [12 x i8] c"'Estoy en '\00", [12 x i8]* %respuesta13, align 1
  %respuesta_ptr13 = getelementptr inbounds [12 x i8], [12 x i8]* %respuesta13, i32 0, i32 0
  call void @write_string(i8* %respuesta_ptr13)
  call void @write_int(i32 %t13)
  %Integer1813 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.newlineFormat, i32 0, i32 0))
  %t14 = add i32 %t11, 1
  store i32 %t14, i32* @i
  br label %L6
L8:
  %t15 = load i32, i32* @y
  store i32 1, i32* @j
  br label %L9
L9:
  %t16 = load i32, i32* @j
  %t17 = icmp sle i32 %t16, %t15
  br i1 %t17, label %L10, label %L11
L10:
  %t18 = load i32, i32* @j
  %respuesta18 = alloca [12 x i8], align 1
  store [12 x i8] c"'Estoy en '\00", [12 x i8]* %respuesta18, align 1
  %respuesta_ptr18 = getelementptr inbounds [12 x i8], [12 x i8]* %respuesta18, i32 0, i32 0
  call void @write_string(i8* %respuesta_ptr18)
  call void @write_int(i32 %t18)
  %Integer5918 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.newlineFormat, i32 0, i32 0))
  %t19 = load i32, i32* @cont
  %t20 = add i32 %t19, 1
  store i32 %t20, i32* @cont
  %t21 = add i32 %t16, 1
  store i32 %t21, i32* @j
  br label %L9
L11:
  store i32 1, i32* @cont
  br label %L12
L12:
  %t22 = load i32, i32* @cont
  %t23 = load i32, i32* @lenght
  %t24 = add i32 %t23, 1
  %t25 = icmp ne i32 %t22, %t24
  br i1 %t25, label %L13, label %L14
L13:
  %resultado160 = alloca [13 x i8], align 1
  store [13 x i8] c"'Entro aqui'\00", [13 x i8]* %resultado160, align 1
  %resultado160_ptr = getelementptr inbounds [13 x i8], [13 x i8]* %resultado160, i32 0, i32 0
  call void @write_string(i8* %resultado160_ptr)
  %_230 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.newlineFormat, i32 0, i32 0))
  %t26 = load i32, i32* @cont
  %t27 = add i32 %t26, 1
  store i32 %t27, i32* @cont
  br label %L12
L14:
  %resultado216 = alloca [27 x i8], align 1
  store [27 x i8] c"'Gracias por usar Pascal!'\00", [27 x i8]* %resultado216, align 1
  %resultado216_ptr = getelementptr inbounds [27 x i8], [27 x i8]* %resultado216, i32 0, i32 0
  call void @write_string(i8* %resultado216_ptr)
  %_29 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.newlineFormat, i32 0, i32 0))
  ret i32 0
}
