package antlr4;

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

    public void generarCodigo(){
        StringBuilder codigo = new StringBuilder();
        codigo.append(tablaSimbolos.generarCodigoLLVMGlobal());
        codigo.append("declare void @writeln(i8*) #1\n");
        codigo.append("\n");
        codigo.append("; Main\n");
        codigo.append("define i32 @main() {\n");





        codigo.append("    ret i32 0\n");
        codigo.append("}\n");

        System.out.println(codigo.toString());

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
                if(simbolo.getType().equalsIgnoreCase("integer")){
                    sb.append("  store i32 ").append(arg1).append(", i32* @").append(resultado).append("\n");
                } else if(simbolo.getType().equalsIgnoreCase("string")){
                    sb.append("  store i8* ").append(arg1).append(", i8** @").append(resultado).append("\n");
                } else if(simbolo.getType().equalsIgnoreCase("character")){
                    sb.append("  store i8 ").append(arg1).append(", i8* @").append(resultado).append("\n");
                } else if(simbolo.getType().equalsIgnoreCase("boolean")){
                    sb.append("  store i1 ").append(arg1).append(", i1* @").append(resultado).append("\n");
                }
//                else if(simbolo.getType().equalsIgnoreCase("array")){
//                    SimboloArreglo simboloArreglo = (SimboloArreglo) simbolo;
//                    int[] sizes = simboloArreglo.getSizes(); // Obtener índices si existen
//                    if(simboloArreglo.getDimensions() == 1){
//
//
//                    }
//                    else if(simboloArreglo.getDimensions() == 2){
//
//                    }
//
//                }
            }
        }
        if(operador.equals("Label")){
            sb.append(arg1).append(":\n");
        }
        if(operador.equals("+")){
            if(arg1.contains("t") && arg2.contains("t")){
                sb.append("  %").append(resultado).append(" = add i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if(arg1.contains("t") && !arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = add i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = add i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.contains("t") && arg2.contains("t")){
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
            if(arg1.contains("t") && arg2.contains("t")){
                sb.append("  %").append(resultado).append(" = sub i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if(arg1.contains("t") && !arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = sub i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = sub i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.contains("t") && arg2.contains("t")){
                sb.append("  %").append(resultado).append(" = sub i32 ").append(arg1).append(", %").append(arg2).append("\n");
            }
            else{
                sb.append("  %").append(resultado).append(" = sub i32 ").append(arg1).append(", ").append(arg2).append("\n");
            }

        }
        if(operador.equals("*")){
            if(arg1.contains("t") && arg2.contains("t")){
                sb.append("  %").append(resultado).append(" = mul i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if(arg1.contains("t") && !arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = mul i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = mul i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.contains("t") && arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = mul i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = mul i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                sb.append("  %").append(resultado).append(" = mul i32 ").append(arg1).append(", ").append(arg2).append("\n");
            }

        }
        if(operador.equals("/")){
            if(arg1.contains("t") && arg2.contains("t")){
                sb.append("  %").append(resultado).append(" = sdiv i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if(arg1.contains("t") && !arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = sdiv i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = sdiv i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.contains("t") && arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = sdiv i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = sdiv i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                sb.append("  %").append(resultado).append(" = sdiv i32 ").append(arg1).append(", ").append(arg2).append("\n");
            }

        }
        if(operador.equals("mod")){
            if(arg1.contains("t") && arg2.contains("t")){
                sb.append("  %").append(resultado).append(" = srem i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if(arg1.contains("t") && !arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = srem i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = srem i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.contains("t") && arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = srem i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = srem i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                sb.append("  %").append(resultado).append(" = srem i32 ").append(arg1).append(", ").append(arg2).append("\n");
            }

        }
        if(operador.equals(">=")){
            if(arg1.contains("t") && arg2.contains("t")){
                sb.append("  %").append(resultado).append(" = icmp sge i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if(arg1.contains("t") && !arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sge i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sge i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.contains("t") && arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = icmp sge i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sge i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                sb.append("  %").append(resultado).append(" = icmp sge i32 ").append(arg1).append(", ").append(arg2).append("\n");
            }
        }
        if(operador.equals("<=")){
            if(arg1.contains("t") && arg2.contains("t")){
                sb.append("  %").append(resultado).append(" = icmp sle i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if(arg1.contains("t") && !arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sle i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sle i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.contains("t") && arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = icmp sle i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sle i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                sb.append("  %").append(resultado).append(" = icmp sle i32 ").append(arg1).append(", ").append(arg2).append("\n");
            }
        }
        if(operador.equals(">")){
            if(arg1.contains("t") && arg2.contains("t")){
                sb.append("  %").append(resultado).append(" = icmp sgt i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if(arg1.contains("t") && !arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.contains("t") && arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                sb.append("  %").append(resultado).append(" = icmp sgt i32 ").append(arg1).append(", ").append(arg2).append("\n");
            }

        }
        if(operador.equals("<")){
            if(arg1.contains("t") && arg2.contains("t")){
                sb.append("  %").append(resultado).append(" = icmp slt i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if(arg1.contains("t") && !arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp slt i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp slt i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.contains("t") && arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = icmp slt i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp slt i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                sb.append("  %").append(resultado).append(" = icmp slt i32 ").append(arg1).append(", ").append(arg2).append("\n");
            }

        }
        if(operador.equals("=")){
            if(arg1.contains("t") && arg2.contains("t")){
                sb.append("  %").append(resultado).append(" = icmp eq i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if(arg1.contains("t") && !arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp eq i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp eq i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.contains("t") && arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = icmp eq i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp eq i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                sb.append("  %").append(resultado).append(" = icmp eq i32 ").append(arg1).append(", ").append(arg2).append("\n");
            }

        }
        if(operador.equals("<>")){
            if(arg1.contains("t") && arg2.contains("t")){
                sb.append("  %").append(resultado).append(" = icmp ne i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if(arg1.contains("t") && !arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp ne i32 %").append(arg1).append(", @").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp ne i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.contains("t") && arg2.contains("t")){
                if(tablaSimbolos.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = icmp ne i32 @").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp ne i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                sb.append("  %").append(resultado).append(" = icmp ne i32 ").append(arg1).append(", ").append(arg2).append("\n");
            }

        }
        if(operador.equals("ifFalse")){
            sb.append("  br i1 %").append(arg1).append(", label %").append(resultado).append(", \n");
        }
        if(operador.equals("goto")){
            sb.append("  br label %").append(resultado).append("\n");
        }
        //if(operador.equals())

        return sb.toString();
    }

}
