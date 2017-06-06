package I18n;

import java.util.ListResourceBundle;

/**
 * Created by SlyFox on 19.05.2017.
 */
public class StatsBundle_it_IT extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
        		{ "Boolean", new String( "{0, choice, 0#no | 1#sì }")},
                { "Integer", new String( "{0}")},
                { "String", new String( "{0}")},
                { "LegSizeEnum", new String( "{0, choice, 0#piccolo | 1#media | 2#grande}")},
                { "Time", new String( "{0}")},

                { "true", new String( "sì")},
                { "false", new String( "no")},

                { "Small", new String( "piccolo")},
                { "Medium", new String( "media")},
                { "Big", new String( "grande")},

                { "Success", new String("Success!") },

                { "FileNotFound", new String("File non trovato") },
                { "InputJSON", new String("Inserire nel campo di immissione eleenty in JSON e assicurarsi di avere file corrispondente e impostare la variabile ReadFileDir dell'ambiente") },
                { "CannotCreateObject", new String("Non è possibile creare un oggetto della classe. Class Vozmzhno non ha un costruttore senza parametri. Assicurarsi che il file correttezza") },
                { "AccessObjectDeny", new String("Non è possibile creare un oggetto della classe. Class Vozmzhno non ha un costruttore senza parametri. Assicurarsi che il file correttezza") },
                { "ClassNotFound", new String("La classe non è stato trovato. Gli oggetti di questo Klas non sono supportati!") },
                { "UnknownException", new String("errore non specificato") },
                { "InputParams", new String("nserire i parametri nel nome della classe campo di input") },
                { "Anons", new String("Annuncio!") },


                { "File", new String("il file!") },
                { "Name", new String("nome") },
                { "LegCount", new String("numero di gambe") },
                { "LegIndex", new String("corrente gamba") },
                { "LegSize", new String("numero di scarpe") },
                { "LegWashed", new String("pulizia di gambe") },
                { "LegBarefoot", new String("esistere di calzature") },
                { "LocationName", new String("il nome della località") },
                { "Came", new String("arrivo") },
                { "Wait", new String("aspettativa") },
                { "Delete", new String("rimozione") },
                { "Actions", new String("effetto") },
                { "SelecetCommand", new String("scegliere di comanda") },
                { "Execute", new String("eseguire") },

                { "MenuCame", new String("È venuto") },
                { "MenuWait", new String("Aspettare") },
                { "MenuActions", new String("Azioni") },

                { "ButtonDelete", new String("Elimina") },

                { "Filter", new String("filtro") },
                { "TimeColumn", new String("tempo") },
                { "FilterChoose", new String("scegliere di filtro") },
                { "FilterName", new String("nome") },
                { "FilterLocation", new String("situazione") },
                { "FilterLegCount", new String("numero di gambe") },
                { "FilterSearchForAll", new String("esatto coincidenza") },


                { "FilterLegSize", new String("numero di scarpe") },

                { "FilterBigLeg", new String("grande") },
                { "FilterMiddleLeg", new String("media") },
                { "FilterSmallLeg", new String("piccolo") },


                { "FilterLegWashed", new String("purezza di gambe") },

                { "FilterIsWashedTrue", new String("pulito") },
                { "FilterIsWashedFalse", new String("disordinato") },


                { "FilterLegBarefoot", new String("esistere di calzature") },

                { "FilterIsBarefootTrue", new String("ha scarpe") },
                { "FilterIsBarefootFalse", new String("ha no scarpe") },


                { "FilterCame", new String("arrivo") },

                { "FilterIsCameTrue", new String("sono venuto") },
                { "FilterIsCameFalse", new String("no sono venuto") },


                { "FilterWait", new String("aspettativa") },

                { "FilterIsWaitTrue", new String("si aspetta") },
                { "FilterIsWaitFalse", new String("non si aspetta") },
        };
    }
}
