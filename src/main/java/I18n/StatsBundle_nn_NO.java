package I18n;

import java.util.ListResourceBundle;

/**
 * Created by SlyFox on 19.05.2017.
 */
public class StatsBundle_nn_NO extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                { "Boolean", new String( "{0, choice, 0#ikke | 1#ja }")},
                { "Integer", new String( "{0}")},
                { "String", new String( "{0}")},

                { "Success", new String("suksess") },

                { "FileNotFound", new String("Finner ikke filen") },
                { "InputJSON", new String("Skriv i inntastingsfeltet eleenty i JSON og sørg for at du har passende fil og sette variabelen ReadFileDir miljø") },
                { "CannotCreateObject", new String("Du kan ikke lage et objekt av klassen. Vozmzhno klassen ikke har en konstruktør uten parametre. Kontroller at filen korrekthet") },
                { "AccessObjectDeny", new String("Du kan ikke lage et objekt av klassen. Vozmzhno klassen ikke har en konstruktør uten parametre. Kontroller at filen korrekthet") },
                { "ClassNotFound", new String("Klassen er ikke funnet. Formålene med denne Klas ikke støttes!") },
                { "UnknownException", new String("Uspesifisert feil") },
                { "InputParams", new String("Skriv parametrene i inngangsfeltklassenavn") },
                { "Anons", new String("Анонс!") },


                { "File", new String("Fil!") },
                { "Name", new String("navn") },
                { "LegCount", new String("antall ben") },
                { "LegIndex", new String("ser på foten") },
                { "LegSize", new String("skostørrelse") },
                { "LegWashed", new String("ren fot") },
                { "LegBarefoot", new String("har sko") },
                { "LocationName", new String("stedsnavn") },
                { "Came", new String("kommer") },
                { "Wait", new String("forventning") },
                { "Delete", new String("fjerne!") },
                { "Actions", new String("effekt") },
                { "SelecetCommand", new String("velg lag") },
                { "Execute", new String("utføre") },

                { "MenuCame", new String("Фильтровать") },
                { "MenuWait", new String("Фильтровать") },
                { "MenuActions", new String("Фильтровать") },

                { "ButtonDelete", new String("Фильтровать") },

                { "Filter", new String("Фильтровать") },

                { "FilterChoose", new String("velg filtre") },
                { "FilterName", new String("navn") },
                { "FilterLocation", new String("plassering") },
                { "FilterLegCount", new String("antall ben") },
                { "FilterSearchForAll", new String("uforkortet sammentreff ") },


                { "FilterLegSize", new String("skostørrelse") },

                { "FilterBigLeg", new String("stor") },
                { "FilterMiddleLeg", new String("gjennomsnittlig") },
                { "FilterSmallLeg", new String("liten") },


                { "FilterLegWashed", new String("ren fot") },

                { "FilterIsWashedTrue", new String("ren") },
                { "FilterIsWashedFalse", new String("rotete") },


                { "FilterLegBarefoot", new String("har sko") },

                { "FilterIsBarefootTrue", new String("det er") },
                { "FilterIsBarefootFalse", new String("har ikke") },


                { "FilterCame", new String("kommer") },

                { "FilterIsCameTrue", new String("jeg kom") },
                { "FilterIsCameFalse", new String("Jeg kom ikke") },


                { "FilterWait", new String("forventning") },

                { "FilterIsWaitTrue", new String("venter på") },
                { "FilterIsWaitFalse", new String("ikke vente") },
        };
    }
}