@lenght = common global i32 0, align 4

@i = common global i32 0, align 4

@j = common global i32 0, align 4

@x = common global i32 0, align 4

@y = common global i32 0, align 4

@cont = common global i32 0, align 4

@arreglo = global [50 x i32 0], align 16

@arreglo_bidi = global [10 x [5 x i32 0]], align 16

@s = common global i8* null, align 8

@bool = common global i1 0, align 1

define i32 @f() {
entry:
  ret i32 3

}

define void @f2(i32 %a, i32 %b) {
entry:
  %_ = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.stringFormat, i32 0, i32 0), i8* getelementptr inbounds ([6 x i8], [6 x i8]* @.str6, i32 0, i32 0))
  %_ = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.newlineFormat, i32 0, i32 0))

}

declare i32 @printf(i8*, ...) #1
declare void @writeln(i8*) #2

; Main
define i32 @main() {
  store i32 50, i32* @lenght
  store i32 10, i32* @x
  store i32 5, i32* @y
  store i32 1, i32* @cont
  store i1 TRUE, i1* @bool
  %t0 = load i32, i32* @x
  store i32 %t0, i32* @i
  br label %L0
L0:
  %t1 = icmp sge i32 @i, 1
  br i1 %t1, label %L1, label %L2
L1:
  %t2 = load i32, i32* @y
  store i32 %t2, i32* @j
  br label %L3
L3:
  %t3 = icmp sge i32 @j, 1
  br i1 %t3, label %L4, label %L5
L4:
  %t4 = load i32, i32* @cont
  %t5 = load i32, i32* @x
  %t6 = load i32, i32* @y
  %t7 = sub i32 %t5, %t6
  %t8 = add i32 %t7, 5
  %t9 = add i32 %t4, %t8
  %t10 = srem i32 %t9, 15
  store i32 %t10, i32* @arreglo_bidi
  %t11 = sub i32 @j, 1
  store i32 %t11, i32* @j
  br label %L3
L5:
  %t12 = sub i32 @i, 1
  store i32 %t12, i32* @i
  br label %L0
L2:
  store i32 1, i32* @cont
  %t13 = load i32, i32* @x
  store i32 1, i32* @i
  br label %L6
L6:
  %t14 = icmp sle i32 @i, %t13
  br i1 %t14, label %L7, label %L8
L7:
  %t15 = load i32, i32* @y
  store i32 1, i32* @j
  br label %L9
L9:
  %t16 = icmp sle i32 @j, %t15
  br i1 %t16, label %L10, label %L11
L10:
  %t17 = load i32, i32* @arreglo_bidi
  store i32 %t17, i32* @arreglo
  %t18 = load i32, i32* @cont
  %t19 = add i32 %t18, 1
  store i32 %t19, i32* @cont
  %t20 = add i32 @j, 1
  store i32 %t20, i32* @j
  br label %L9
L11:
  %t21 = add i32 @i, 1
  store i32 %t21, i32* @i
  br label %L6
L8:
  store i32 1, i32* @cont
  br label %L12
L12:
  %t22 = load i32, i32* @cont
  %t23 = load i32, i32* @lenght
  %t24 = add i32 %t23, 1
  %t25 = icmp ne i32 %t22, %t24
  br i1 %t25, label %L13, label %L14
L13:
  %_ = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.stringFormat, i32 0, i32 0), i8* getelementptr inbounds ([16 x i8], [16 x i8]* @.str16, i32 0, i32 0))
  %_ = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.stringFormat, i32 0, i32 0), i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str4, i32 0, i32 0))
  %_ = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.newlineFormat, i32 0, i32 0))
  %t26 = load i32, i32* @cont
  %t27 = add i32 %t26, 1
  store i32 %t27, i32* @cont
  br label %L12
L14:
  %_ = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.stringFormat, i32 0, i32 0), i8* getelementptr inbounds ([26 x i8], [26 x i8]* @.str26, i32 0, i32 0))
  %_ = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.newlineFormat, i32 0, i32 0))
    ret i32 0
}
