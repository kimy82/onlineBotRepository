$(function() {
    var availableTowns = [
        "Girona",
        "Figueres",
        "Lloret de mar",
        "Blanes",
        "Olot",
        "Salt",
        "Palafrugell",
        "Sant Feliu de Gu�xols",
        "Roses",
        "Banyoles",
        "Palam�s",
        "Santa Coloma de Farners",
        "Castell� d'Emp�ries",
        "Torroella de Montgr�",
        "Ripoll",
        "Calonge",
        "L'Escala",
        "La Bisbal d'Empord�",
        "Castell-Platja d'Aro",
        "Cass� de la Selva",
        "Puigcerd�",
        "Llagostera",
        "Vidreres",
        "Ma�anet de la Selva",
        "Caldes de Malavella",
        "Arb�cies",
        "Tossa de Mar",
        "Sant Hilari Sacalm",
        "Angl�s",
        "Sils",
        "Vilafant",
        "Llan�a",
        "Santa Cristina d'Aro",
        "Celr�",
        "Sarri� de Ter",
        "Bescan�",
        "Porqueres",
        "Begur",
        "Hostalric",
        "Riells i Viabrea",
        "Breda",
        "Sant Joan de les Abadesses",
        "Campdev�nol ",
        "Sant Juli� de Ramis", 	
        "Sant Gregori", 	
        "Quart", 	
        "La Jonquera", 	
        "Vilob� d'Onyar", 	
        "Cadaqu�s",
        "La Vall d'en Bas",
        "Sant Joan les Fonts",
        "Pals",
        "Camprodon",
        "Vilablareix",
        "Fornells de la Selva",
        "Besal�",
        "Amer",
        "Cornell� del Terri",
        "Riudarenes",
        "Sant Pere Pescador",
        "La Cellera de Ter",
        "Riudellots de la Selva",
        "Ribes de Freser",
        "Peralada",
        "Mont-ras",
        "Forallac",
        "Les Preses",
        "Alp",
        "Bordils",
        "Les Planes d'Hostoles",
        "Ll�via",
        "Santa Pau",
        "Avinyonet de Puigvent�s",
        "Palau-saverdera",
        "Fontcoberta",
        "Sant Feliu de Pallerols",
        "Cor��",
        "La Vall de Bianya",
        "Portbou",
        "Cru�lles, Monells i Sant Sadurn� de l'Heura",
        "Sant Juli� del Llor i Bonmat�",
        "Navata",
        "Verges",
        "Llers",
        "Vilaju�ga",
        "Seriny�",
        "Vilamalla",
        "Viladrau",
        "Ull�",
        "Fla��",
        "El Port de la Selva",
        "Castellfollit de la Roca",
        "Montagut i Oix",
        "Cabanes",
        "Vilabertran",
        "Cervi� de Ter",
        "Vall-llobrega",
        "L'Armentera",
        "Garriguella",
        "Agullana",
        "Ventall�",
        "Saus, Camallera i Llampaies",
        "Vilademuls",
        "Sant Jaume de Llierca",
        "Sant Feliu de Buixalleu",
        "Sant Miquel de Fluvi�",
        "Tortell�",
        "Aiguaviva",
        "Ma�anet de Cabrenys",
        "Llambilles",
        "Llad�",
        "Albons",
        "Borrass�",
        "Massanes",
        "Torroella de Fluvi�",
        "Sant Pau de Seg�ries",
        "Cam�s",
        "Sant Jordi Desvalls",
        "Bellcaire d'Empord�",
        "Forti�",
        "Vila-sacra",
        "Capmany",
        "Canet d'Adri",
        "Colera",
        "Sant Mart� de Ll�mena",
        "Pau",
        "Guils de Cerdanya",
        "Llanars",
        "El Far d'Empord�",
        "Sant Climent Sescebes",
        "Darnius",
        "Sant Joan de Mollet",
        "Pont de Molins",
        "Campllong",
        "Vilallonga de Ter",
        "Palol de Revardit",
        "Ger",
        "Fontanals de Cerdanya",
        "Viladamat",
        "Osor",
        "Esponell�",
        "La Tallada d'Empord�",
        "Riudaura",
        "La Pera",
        "Mai� de Montcal",
        "Argelaguer",
        "Espolla",
        "Jafre",
        "Bolvir",
        "Brunyola",
        "Garrig�s",
        "Parlav�",
        "Ordis",
        "Vilanant",
        "Gualta",
        "Moll�",
        "Sant Aniol de Finestres",
        "Santa Llogaia d'�lguema",
        "Jui�",
        "Mieres",
        "Cantallops",
        "Is�vol",
        "Foix�",
        "Terrades",
        "Planoles",
        "Regenc�s",
        "Vilamacolum",
        "Masarac",
        "Palau-sator",
        "Cistella",
        "Ullastret",
        "Crespi�",
        "Madremanya",
        "Sant Mart� Vell",
        "Boadella i les Escaules",
        "Ogassa",
        "Biure",
        "Cabanelles",
        "Riumors",
        "Rupi�",
        "Sant Miquel de Campmajor",
        "Pont�s",
        "Sant Ferriol",
        "Sant Lloren� de la Muga",
        "Vallfogona de Ripoll�s",
        "Les Llosses",
        "Das",
        "Vilopriu",
        "Ultramort",
        "Viladasens",
        "Gombr�n",
        "La Selva de Mar",
        "Serra de Dar�",
        "Ur�s",
        "Espinelves",
        "Colomers",
        "Rab�s",
        "Sant Mori",
        "Torrent",
        "Queralbs",
        "Siurana",
        "Mollet de Peralada",
        "Setcases",
        "Pedret i Marz�",
        "Vilamaniscle",
        "Beuda",
        "Fontanilles",
        "Vidr�",
        "Garrigoles",
        "Toses",
        "Pardines",
        "Sant Andreu Salou",
        "Albany�",
        "Vila�r",
        "Sales de Llierca",
        "Campelles",
        "Susqueda",
        "Palau de Santa Eul�lia",
        "La Vajol",
        "Meranges"
    ];
    $( "#poble" ).autocomplete({
        source: availableTowns
    });
});