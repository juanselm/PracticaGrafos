                for(int i = 1; i < campos.length; i++){
                   if(!campos[i].equals("0") && i != j){
                       matriz.crearAdyacencia(i, j, 50);
                   }
               }