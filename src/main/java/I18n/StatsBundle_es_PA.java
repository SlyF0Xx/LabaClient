package I18n;

import java.util.ListResourceBundle;

/**
 * Created by SlyFox on 19.05.2017.
 */
public class StatsBundle_es_PA extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
       return new Object[][]{
               { "Boolean", new String( "{0, choice, 0#no | 1#sí }")},
               { "Integer", new String( "{0}")},
               { "String", new String( "{0}")},
               { "LegSizeEnum", new String( "{0, choice, 0#pequeño | 1#promedio | 2#grande }")},

               { "true", new String( "sí")},
               { "false", new String( "no")},

               { "Small", new String( "pequeño")},
               { "Medium", new String( "promedio")},
               { "Big", new String( "grande")},

               { "Success", new String("Éxito!") },

               { "FileNotFound", new String("archivo no encontrado") },
               { "InputJSON", new String("Introduzca en el campo de entrada eleenty en JSON y asegurarse de que tiene el archivo a juego y establece la variable de entorno ReadFileDir") },
               { "CannotCreateObject", new String("No se puede crear un objeto de la clase. Vozmzhno clase no tiene un constructor sin parámetros. Asegúrese de que el archivo de corrección") },
               { "AccessObjectDeny", new String("No se puede crear un objeto de clase. Vozmzhno clase no tiene un constructor sin parámetros. Asegúrese de que el archivo de corrección") },
               { "ClassNotFound", new String("La clase no se encuentra. Los objetos de esta Klas no son compatibles!") },
               { "UnknownException", new String("Error no especificado") },
               { "InputParams", new String("Introduzca los parámetros en el nombre de la clase campo de entrada") },
               { "Anons", new String("anuncio!") },



               { "File", new String("archivo") },
                { "Name", new String("nombre") },
                { "LegCount", new String("Número de patas") },
                { "LegIndex", new String("Pierna actual") },
                { "LegSize", new String("tamaño del zapato") },
                { "LegWashed", new String("Limpieza de los pies") },
                { "LegBarefoot", new String("Disponibilidad de calzado") },
                { "LocationName", new String("Topónimo") },
                { "Came", new String("la venida") },
                { "Wait", new String("Espera") },
                { "Delete", new String("borrar") },
                { "Actions", new String("acción") },
                { "SelecetCommand", new String("Elegir comando") },
                { "Execute", new String("Ejecutar") },

                { "MenuCame", new String("vino") },
                { "MenuWait", new String("Espere") },
                { "MenuActions", new String("Comportamiento") },

                { "ButtonDelete", new String("Borrar") },

                { "Filter", new String("filtro") },

                { "FilterChoose", new String("Elija los filtros") },
                { "FilterName", new String("nombre") },
                { "FilterLocation", new String("ubicación") },
                { "FilterLegCount", new String("Número de pies") },
                { "FilterSearchForAll", new String("Concurrencias exactas") },


                { "FilterLegSize", new String("tamaño del zapato") },

                { "FilterBigLeg", new String("grande") },
                { "FilterMiddleLeg", new String("medio") },
                { "FilterSmallLeg", new String("pequeña") },


                { "FilterLegWashed", new String("Limpieza de los pies") },

                { "FilterIsWashedTrue", new String("limpio") },
                { "FilterIsWashedFalse", new String("sucio") },


                { "FilterLegBarefoot", new String("Disponibilidad de calzad") },

                { "FilterIsBarefootTrue", new String("tiene") },
                { "FilterIsBarefootFalse", new String("No tiene") },


                { "FilterCame", new String("La venida") },

                { "FilterIsCameTrue", new String("llegado") },
                { "FilterIsCameFalse", new String("No llegó") },


                { "FilterWait", new String("Espera") },

                { "FilterIsWaitTrue", new String("Eso espera") },
                { "FilterIsWaitFalse", new String("Eso no espera") },
        };
    }
}