package antlr4;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GenerarCodigoLLVM {
    Codigo3Direcciones codigo3Direcciones;
    TablaSimbolos tablaSimbolos;
    String nombreArchivo;
    String currentScope = "global";
    String previousScope = "";

    public GenerarCodigoLLVM(String nombreArchivo, Codigo3Direcciones codigo3Direcciones, TablaSimbolos tablaSimbolos) {
        this.nombreArchivo = nombreArchivo;
        this.codigo3Direcciones = codigo3Direcciones;
        this.tablaSimbolos = tablaSimbolos;
    }
    public GenerarCodigoLLVM( Codigo3Direcciones codigo3Direcciones, TablaSimbolos tablaSimbolos) {
        this.codigo3Direcciones = codigo3Direcciones;
        this.tablaSimbolos = tablaSimbolos;
    }
    public GenerarCodigoLLVM( Codigo3Direcciones codigo3Direcciones) {
        this.codigo3Direcciones = codigo3Direcciones;
    }

    public void generarCodigo(){
        StringBuilder codigo = new StringBuilder();
        codigo.append("@str_fmt = constant [3 x i8] c\"%d\\00\", align 1     ; Formato para enteros\n");
        codigo.append("@char_fmt = constant [3 x i8] c\"%c\\00\", align 1     ; Formato para caracteres\n");
        codigo.append("@.newlineFormat = constant [2 x i8] c\"\\0A\\00\", align 1\n");
        codigo.append("\n");
        codigo.append("; Declaración de variables globales de Codigo Fuente\n");
        codigo.append(tablaSimbolos.generarCodigoLLVMGlobal());

        codigo.append("define void @write_int(i32 %num) {\n" +
                "    call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @str_fmt, i32 0, i32 0), i32 %num)\n" +
                "    ret void\n" +
                "}\n");
        codigo.append("define void @write_char(i8 %char) {\n" +
                "    call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @char_fmt, i32 0, i32 0), i8 %char)\n" +
                "    ret void\n" +
                "}\n");
        codigo.append("define void @write_string(i8* %str) {\n" +
                "    call i32 @puts(i8* %str)\n" +
                "    ret void\n" +
                "}\n");
        codigo.append("define void @writeln(i8* %str) {\n" +
                "    ; Imprimir la cadena\n" +
                "    call i32 @puts(i8* %str)\n" +
                "    \n" +
                "    ; Imprimir un salto de línea\n" +
                "    call void @write_char(i8 10)  ; 10 es el código ASCII para el salto de línea '\\n'\n" +
                "    \n" +
                "    ret void\n" +
                "}\n");
        codigo.append("declare i32 @printf(i8*, ...) #1\n");
        codigo.append("declare i32 @atoi(i8*)\n" +
                "declare i32 @sprintf(i8*, i8*, ...)\n" +
                "declare i32 @puts(i8*)\n" +
                "declare void @exit(i32)");
        codigo.append("\n");
        codigo.append("; Main\n");
        codigo.append("define i32 @main() {\n");
        for (Quintuplo q : codigo3Direcciones.listaQuintuplos) {
            codigo.append(convertirALLVM(q));
        }
        codigo.append("  ret i32 0\n");
        codigo.append("}\n");

        // Ruta completa del archivo
        File directorio = new File("Salidas LL");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado: " + directorio.getAbsolutePath());
            } else {
                System.err.println("No se pudo crear el directorio: " + directorio.getAbsolutePath());
                return;
            }
        }
        File archivo = new File(directorio, nombreArchivo);

        // Escribir en el archivo
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(codigo.toString());
            System.out.println("Código escrito en el archivo: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }

    }

    public String convertirALLVM(Quintuplo q) {
        StringBuilder sb = new StringBuilder();

        String operador = q.getOperador();
        String arg1 = q.getArg1();
        String arg2 = q.getArg2();
        String resultado = q.getResultado();

        if(operador.equals(":=")){
            if(tablaSimbolos.findSimbolo(resultado)){
                Simbolo simbolo = tablaSimbolos.getSimbolo(resultado, currentScope);
                if(arg1.startsWith("t") && arg1.length() <= 3){
                    if(simbolo.getType().equalsIgnoreCase("integer")){
                        sb.append("  store i32 %").append(arg1).append(", i32* @").append(resultado).append("\n");
                    } else if(simbolo.getType().equalsIgnoreCase("string")){
                        sb.append("  store i8* %").append(arg1).append(", i8** @").append(resultado).append("\n");
                    } else if(simbolo.getType().equalsIgnoreCase("character")){
                        sb.append("  store i8 %").append(arg1).append(", i8* @").append(resultado).append("\n");
                    } else if(simbolo.getType().equalsIgnoreCase("boolean")){
                        sb.append("  store i1 %").append(arg1).append(", i1* @").append(resultado).append("\n");
                    }
                }
                else{
                    if(simbolo.getType().equalsIgnoreCase("integer")){
                        sb.append("  store i32 ").append(arg1).append(", i32* @").append(resultado).append("\n");
                    } else if(simbolo.getType().equalsIgnoreCase("string")){
                        sb.append("  store i8* ").append(arg1).append(", i8** @").append(resultado).append("\n");
                    } else if(simbolo.getType().equalsIgnoreCase("character")){
                        sb.append("  store i8 ").append(arg1).append(", i8* @").append(resultado).append("\n");
                    } else if(simbolo.getType().equalsIgnoreCase("boolean")){
                        sb.append("  store i1 ").append(arg1).append(", i1* @").append(resultado).append("\n");
                    }
                }

            }
        }
        if(operador.equals("label")){
            sb.append(arg1).append(":\n");
        }
        if(operador.equals("load")){
            if(tablaSimbolos.findSimbolo(arg1)){
                Simbolo simbolo = tablaSimbolos.getSimbolo(arg1, currentScope);
                if(simbolo.getType().equalsIgnoreCase("integer")){
                    sb.append("  %").append(resultado).append(" = load i32, i32* @").append(arg1).append("\n");
                } else if(simbolo.getType().equalsIgnoreCase("string")){
                    sb.append("  %").append(resultado).append(" = load i8*, i8** @").append(arg1).append("\n");
                } else if(simbolo.getType().equalsIgnoreCase("character")){
                    sb.append("  %").append(resultado).append(" = load i8, i8* @").append(arg1).append("\n");
                } else if(simbolo.getType().equalsIgnoreCase("boolean")){
                    sb.append("  %").append(resultado).append(" = load i1, i1* @").append(arg1).append("\n");
                }
            }
        }
        if(operador.equals("+")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = add i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = add i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = add i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = add i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = add i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                if(tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = add i32 @").append(arg1).append(", @").append(arg2).append("\n");
                }
                else if(tablaSimbolos.findSimbolo(arg1) && !tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = add i32 @").append(arg1).append(", ").append(arg2).append("\n");
                }
                else if(!tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = add i32 ").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = add i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }
            }

        }
        if(operador.equals("-")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = sub i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = sub i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = sub i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = sub i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else {
                    sb.append("  %").append(resultado).append(" = sub i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                if(tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = sub i32 @").append(arg1).append(", @").append(arg2).append("\n");
                }
                else if(tablaSimbolos.findSimbolo(arg1) && !tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = sub i32 @").append(arg1).append(", ").append(arg2).append("\n");
                }
                else if(!tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = sub i32 ").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = sub i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }


            }

        }
        if(operador.equals("*")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = mul i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = mul i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = mul i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = mul i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = mul i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                if(tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = mul i32 @").append(arg1).append(", @").append(arg2).append("\n");
                }
                else if(tablaSimbolos.findSimbolo(arg1) && !tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = mul i32 @").append(arg1).append(", ").append(arg2).append("\n");
                }
                else if(!tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = mul i32 ").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = mul i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }

            }

        }
        if(operador.equals("/")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = sdiv i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = sdiv i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = sdiv i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = sdiv i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = sdiv i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                if(tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = sdiv i32 @").append(arg1).append(", @").append(arg2).append("\n");
                }
                else if(tablaSimbolos.findSimbolo(arg1) && !tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = sdiv i32 @").append(arg1).append(", ").append(arg2).append("\n");
                }
                else if(!tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = sdiv i32 ").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = sdiv i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }

            }

        }
        if(operador.equals("mod")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = srem i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = srem i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = srem i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = srem i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = srem i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                if(tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = srem i32 @").append(arg1).append(", @").append(arg2).append("\n");
                }
                else if(tablaSimbolos.findSimbolo(arg1) && !tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = srem i32 @").append(arg1).append(", ").append(arg2).append("\n");
                }
                else if(!tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = srem i32 ").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = srem i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }
            }

        }
        if(operador.equals(">=")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = icmp sge i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sge i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sge i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = icmp sge i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sge i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                if(tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sge i32 @").append(arg1).append(", @").append(arg2).append("\n");
                }
                else if(tablaSimbolos.findSimbolo(arg1) && !tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sge i32 @").append(arg1).append(", ").append(arg2).append("\n");
                }
                else if(!tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sge i32 ").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sge i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
        }
        if(operador.equals("<=")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = icmp sle i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sle i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sle i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = icmp sle i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sle i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                if(tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sle i32 @").append(arg1).append(", @").append(arg2).append("\n");
                }
                else if(tablaSimbolos.findSimbolo(arg1) && !tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sle i32 @").append(arg1).append(", ").append(arg2).append("\n");
                }
                else if(!tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sle i32 ").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sle i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
        }
        if(operador.equals(">")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = icmp sgt i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                if(tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 @").append(arg1).append(", @").append(arg2).append("\n");
                }
                else if(tablaSimbolos.findSimbolo(arg1) && !tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 @").append(arg1).append(", ").append(arg2).append("\n");
                }
                else if(!tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 ").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }
            }

        }
        if(operador.equals("<")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = icmp slt i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp slt i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp slt i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = icmp slt i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp slt i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                if(tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp slt i32 @").append(arg1).append(", @").append(arg2).append("\n");
                }
                else if(tablaSimbolos.findSimbolo(arg1) && !tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp slt i32 @").append(arg1).append(", ").append(arg2).append("\n");
                }
                else if(!tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp slt i32 ").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp slt i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }
            }

        }
        if(operador.equals("=")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = icmp eq i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp eq i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp eq i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = icmp eq i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp eq i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else {
                if (tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)) {
                    sb.append("  %").append(resultado).append(" = icmp eq i32 @").append(arg1).append(", @").append(arg2).append("\n");
                } else if (tablaSimbolos.findSimbolo(arg1) && !tablaSimbolos.findSimbolo(arg2)) {
                    sb.append("  %").append(resultado).append(" = icmp eq i32 @").append(arg1).append(", ").append(arg2).append("\n");
                } else if (!tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)) {
                    sb.append("  %").append(resultado).append(" = icmp eq i32 ").append(arg1).append(", @").append(arg2).append("\n");
                } else {
                    sb.append("  %").append(resultado).append(" = icmp eq i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }
            }

        }
        if(operador.equals("<>")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = icmp ne i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp ne i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp ne i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = icmp ne i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp ne i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else {
                if (tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)) {
                    sb.append("  %").append(resultado).append(" = icmp ne i32 @").append(arg1).append(", @").append(arg2).append("\n");
                } else if (tablaSimbolos.findSimbolo(arg1) && !tablaSimbolos.findSimbolo(arg2)) {
                    sb.append("  %").append(resultado).append(" = icmp ne i32 @").append(arg1).append(", ").append(arg2).append("\n");
                } else if (!tablaSimbolos.findSimbolo(arg1) && tablaSimbolos.findSimbolo(arg2)) {
                    sb.append("  %").append(resultado).append(" = icmp ne i32 ").append(arg1).append(", @").append(arg2).append("\n");
                } else {
                    sb.append("  %").append(resultado).append(" = icmp ne i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }
            }

        }
        if(operador.equals("ifFalse")){
            sb.append("  br i1 %").append(arg1).append(", label %").append(arg2).append(", label %").append(resultado).append("\n");
        }
        if(operador.equals("goto")){
            sb.append("  br label %").append(arg1).append("\n");
        }
        if(operador.equals("call")){
            previousScope = currentScope;
            currentScope = arg1;
            tablaSimbolos.enterScope(arg1);
            if(tablaSimbolos.findSimbolo(arg1)){
                SimboloFuncion simboloFuncion = (SimboloFuncion) tablaSimbolos.getSimbolo(arg1, currentScope);
                if(simboloFuncion.getType().equalsIgnoreCase("integer")){
                    sb.append("  %").append(resultado).append(" = call i32 @").append(arg1).append("(");
                } else if(simboloFuncion.getType().equalsIgnoreCase("string")){
                    sb.append("  %").append(resultado).append(" = call i8* @").append(arg1).append("(");
                } else if(simboloFuncion.getType().equalsIgnoreCase("character")){
                    sb.append("  %").append(resultado).append(" = call i8 @").append(arg1).append("(");
                } else if(simboloFuncion.getType().equalsIgnoreCase("boolean")){
                    sb.append("  %").append(resultado).append(" = call i1 @").append(arg1).append("(");
                } else if(simboloFuncion.getType().equalsIgnoreCase("procedure")){
                    sb.append("  call void @").append(arg1).append("(");
                }
                for (int i = 0; i < simboloFuncion.parameters.size(); i++) {
                    Simbolo parametro = simboloFuncion.parameters.get(i);
                    String[] parametrosEnviados = arg2.split(",");
                    if(tablaSimbolos.findSimbolo(parametrosEnviados[i])){
                        String parametroAEnviar = parametrosEnviados[i].replace(" ", "");
                        if(parametro.getType().equalsIgnoreCase("integer")){
                            sb.append("i32 @").append(parametroAEnviar);
                        } else if(parametro.getType().equalsIgnoreCase("string")){
                            sb.append("i8* @").append(parametroAEnviar);
                        } else if(parametro.getType().equalsIgnoreCase("character")){
                            sb.append("i8 @").append(parametroAEnviar);
                        } else if(parametro.getType().equalsIgnoreCase("boolean")){
                            sb.append("i1 @").append(parametroAEnviar);
                        }
                    }
                    else{
                        String parametroAEnviar = parametrosEnviados[i].replace(" ", "");
                        if(parametro.getType().equalsIgnoreCase("integer")){
                            if(parametroAEnviar.startsWith("t") && parametroAEnviar.length() <= 3){
                                sb.append("i32 %").append(parametroAEnviar);
                            }
                            else{
                                sb.append("i32 ").append(parametroAEnviar);
                            }
                        } else if(parametro.getType().equalsIgnoreCase("string")){
                            if(parametroAEnviar.startsWith("t") && parametroAEnviar.length() <= 3){
                                sb.append("i8*").append(" %").append(parametroAEnviar);
                            }
                            else{
                                sb.append("i8* ").append(parametroAEnviar);
                            }

                        } else if(parametro.getType().equalsIgnoreCase("character")){
                            if(parametroAEnviar.startsWith("t") && parametroAEnviar.length() <= 3){
                                sb.append("i8").append(" %").append(parametroAEnviar);
                            }
                            else{
                                sb.append("i8 ").append(parametroAEnviar);
                            }

                        } else if(parametro.getType().equalsIgnoreCase("boolean")){
                            if(parametroAEnviar.startsWith("t") && parametroAEnviar.length() <= 3){
                                sb.append("i1").append(" %").append(parametroAEnviar);
                            }
                            else{
                                sb.append("i1 ").append(parametroAEnviar);
                            }

                        }
                    }

                    if(i < simboloFuncion.parameters.size() - 1){
                        sb.append(", ");
                    }


                }
                sb.append(")\n");
            }
            currentScope = previousScope;
            tablaSimbolos.exitScope();

        }
        if (operador.equals("write")) {
            // Si 'arg2' no es nulo, significa que hay algo que imprimir además de la cadena
            if (arg2 != null) {
                // Definimos la cadena temporal con % en vez de @
                String numeroArg2 = arg2.substring(1); // Eliminar '%' de 'arg2' para crear un nombre dinámico
                sb.append("  %respuesta").append(numeroArg2).append(" = alloca [")
                        .append(arg1.length() + 1)
                        .append(" x i8], align 1\n");

                sb.append("  store [")
                        .append(arg1.length() + 1)
                        .append(" x i8] c\"")
                        .append(arg1)
                        .append("\\00\", [")
                        .append(arg1.length() + 1)
                        .append(" x i8]* %respuesta").append(numeroArg2).append(", align 1\n");

                // Usamos getelementptr para obtener el puntero al primer carácter de la cadena
                sb.append("  %respuesta_ptr").append(numeroArg2).append(" = getelementptr inbounds [")
                        .append(arg1.length() + 1)
                        .append(" x i8], [")
                        .append(arg1.length() + 1)
                        .append(" x i8]* %respuesta").append(numeroArg2).append(", i32 0, i32 0\n");

                // Imprimimos la cadena 'arg1' usando el puntero obtenido
                sb.append("  call void @write_string(i8* %respuesta_ptr").append(numeroArg2).append(")\n");

                // 'resultado' contiene el tipo de la variable que se va a imprimir
                String tipo = resultado;
                if (tipo.equalsIgnoreCase("integer")) {
                    // Imprimir un entero
                    sb.append("  call void @write_int(i32 %").append(arg2).append(")\n");
                } else if (tipo.equalsIgnoreCase("character")) {
                    // Imprimir un carácter
                    sb.append("  call void @write_char(i8 %").append(arg2).append(")\n");
                } else if (tipo.equalsIgnoreCase("string")) {
                    // Imprimir una cadena
                    sb.append("  call void @write_string(i8* %").append(arg2).append(")\n");
                }
            } else {
                // Si 'arg2' es nulo, solo imprimimos la cadena 'arg1'
                String numeroArg2 = "resultado"; // Usamos "resultado" como nombre cuando 'arg2' es nulo
                sb.append("  %").append(numeroArg2).append(" = alloca [")
                        .append(arg1.length() + 1)
                        .append(" x i8], align 1\n");

                sb.append("  store [")
                        .append(arg1.length() + 1)
                        .append(" x i8] c\"")
                        .append(arg1)
                        .append("\\00\", [")
                        .append(arg1.length() + 1)
                        .append(" x i8]* %").append(numeroArg2).append(", align 1\n");

                // Usamos getelementptr para obtener el puntero al primer carácter de la cadena
                sb.append("  %").append(numeroArg2).append("_ptr = getelementptr inbounds [")
                        .append(arg1.length() + 1)
                        .append(" x i8], [")
                        .append(arg1.length() + 1)
                        .append(" x i8]* %").append(numeroArg2).append(", i32 0, i32 0\n");

                // Imprimimos la cadena 'arg1' usando el puntero obtenido
                sb.append("  call void @write_string(i8* %").append(numeroArg2).append("_ptr)\n");
            }
        }


        if(operador.equals("writeln")) {
            // Si 'arg2' no es nulo, significa que hay algo que imprimir además de la cadena
            if (arg2 != null) {
                // Definimos la cadena temporal con % en vez de @
                String numeroArg2 = arg2.substring(1); // Eliminar '%' de 'arg2' para crear un nombre dinámico
                sb.append("  %respuesta").append(numeroArg2).append(" = alloca [")
                        .append(arg1.length() + 1)
                        .append(" x i8], align 1\n");

                sb.append("  store [")
                        .append(arg1.length() + 1)
                        .append(" x i8] c\"")
                        .append(arg1)
                        .append("\\00\", [")
                        .append(arg1.length() + 1)
                        .append(" x i8]* %respuesta").append(numeroArg2).append(", align 1\n");

                // Usamos getelementptr para obtener el puntero al primer carácter de la cadena
                sb.append("  %respuesta_ptr").append(numeroArg2).append(" = getelementptr inbounds [")
                        .append(arg1.length() + 1)
                        .append(" x i8], [")
                        .append(arg1.length() + 1)
                        .append(" x i8]* %respuesta").append(numeroArg2).append(", i32 0, i32 0\n");

                // Imprimimos la cadena 'arg1' usando el puntero obtenido
                sb.append("  call void @write_string(i8* %respuesta_ptr").append(numeroArg2).append(")\n");

                // 'resultado' contiene el tipo de la variable que se va a imprimir
                String tipo = resultado;
                if (tipo.equalsIgnoreCase("integer")) {
                    // Imprimir un entero
                    sb.append("  call void @write_int(i32 %").append(arg2).append(")\n");
                } else if (tipo.equalsIgnoreCase("character")) {
                    // Imprimir un carácter
                    sb.append("  call void @write_char(i8 %").append(arg2).append(")\n");
                } else if (tipo.equalsIgnoreCase("string")) {
                    // Imprimir una cadena
                    sb.append("  call void @write_string(i8* %").append(arg2).append(")\n");
                }
            } else {
                // Si 'arg2' es nulo, solo imprimimos la cadena 'arg1'
                String numeroArg2 = "resultado"; // Usamos "resultado" como nombre cuando 'arg2' es nulo
                sb.append("  %").append(numeroArg2).append(" = alloca [")
                        .append(arg1.length() + 1)
                        .append(" x i8], align 1\n");

                sb.append("  store [")
                        .append(arg1.length() + 1)
                        .append(" x i8] c\"")
                        .append(arg1)
                        .append("\\00\", [")
                        .append(arg1.length() + 1)
                        .append(" x i8]* %").append(numeroArg2).append(", align 1\n");

                // Usamos getelementptr para obtener el puntero al primer carácter de la cadena
                sb.append("  %").append(numeroArg2).append("_ptr = getelementptr inbounds [")
                        .append(arg1.length() + 1)
                        .append(" x i8], [")
                        .append(arg1.length() + 1)
                        .append(" x i8]* %").append(numeroArg2).append(", i32 0, i32 0\n");

                // Imprimimos la cadena 'arg1' usando el puntero obtenido
                sb.append("  call void @write_string(i8* %").append(numeroArg2).append("_ptr)\n");
            }

            // Agregar salto de línea al final de la impresión
            sb.append("  %").append(resultado).append(" = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.newlineFormat, i32 0, i32 0))\n");
        }
        if (operador.equals("read")) {
            if (tablaSimbolos.findSimbolo(resultado)) {
                Simbolo simbolo = tablaSimbolos.getSimbolo(resultado, currentScope);
                if (simbolo.getType().equalsIgnoreCase("integer")) {
                    sb.append("  %").append(resultado).append(" = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.intFormat, i32 0, i32 0), i32* @").append(resultado).append(")\n");
                } else if (simbolo.getType().equalsIgnoreCase("string")) {
                    sb.append("  %").append(resultado).append(" = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.stringFormat, i32 0, i32 0), i8** @").append(resultado).append(")\n");
                } else if (simbolo.getType().equalsIgnoreCase("character")) {
                    sb.append("  %").append(resultado).append(" = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.charFormat, i32 0, i32 0), i8* @").append(resultado).append(")\n");
                } else if (simbolo.getType().equalsIgnoreCase("boolean")) {
                    sb.append("  %").append(resultado).append(" = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.boolFormat, i32 0, i32 0), i1* @").append(resultado).append(")\n");
                }
            }
        }

        return sb.toString();
    }
    public void ejecutarCodigo() {
        try {
            // Ruta completa del archivo
            File archivo = new File("Salidas LL", nombreArchivo);
            if (!archivo.exists()) {
                System.err.println("El archivo no existe: " + archivo.getAbsolutePath());
                return;
            }

            // Ejecutar el archivo .ll con lli
            Process proceso = new ProcessBuilder("lli", archivo.getAbsolutePath()).start();

            // Capturar la salida del programa
            BufferedReader salida = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            System.out.println("Salida del programa:");
            while ((linea = salida.readLine()) != null) {
                System.out.println(linea);
            }

            // Capturar errores si los hay
            BufferedReader errores = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
            while ((linea = errores.readLine()) != null) {
                System.err.println("Error: " + linea);
            }

            // Esperar a que el proceso termine
            proceso.waitFor();
            System.out.println("Ejecución finalizada con código: " + proceso.exitValue());
        } catch (IOException | InterruptedException e) {
            System.err.println("Error al ejecutar el código: " + e.getMessage());
        }
    }

}
