/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import models.Feat;

/**
 *
 * @author sergi_000
 */
public class GenerateData {
    
    
     public static void generateData() {
        
        Feat Wiedza = new Feat();
        Wiedza.setName("Wiedza ...");
        Wiedza.setDescription("Czy to w wyniku studiowania książek, słuchania "
                + "nauczycieli czy własnych doświadczeń, bohater zdobył wiedzę na "
                + "dany temat. Atut można brać wiele razy, za każdym razem dla "
                + "innej wiedzy.");
        Wiedza.addStoryRequirement("Doświadczenie, nauka bądź rozmowa");
        
        Feat PodstawyWalki =  new Feat();
        PodstawyWalki.setName("Podstawy Walki");
        PodstawyWalki.setDescription("Bohater uczy się podstaw walki bronią konwencjonalną, "
                + "zarówno białą, jak i dystansową. (Bohater, który od początku potrafił walczyć "
                + "ma ten atut z założenia).");
        PodstawyWalki.addStoryRequirement("Nauka u nauczyciela");
        
        Feat Taktyka = new Feat();
        Taktyka.setName("Taktyka");
        Taktyka.setDescription("Bohater w wyniku wielu walk zdobył doświadczenie "
                + "które pozwala mu na stosowanie różnych taktyk podczas walki. "
                + "Wykupienie tego atutu pozwala na nauczenie się w przyszłości "
                + "specjalistycznych atutów związanych z walką.");
        Taktyka.addRequiredFeat(PodstawyWalki.getId());
        Taktyka.addStoryRequirement("Doświadczenie w walce");
        
        
        Feat PozycjaObronna = new Feat();
        PozycjaObronna.setName("Pozycja Obronna");
        PozycjaObronna.setDescription("Zdyscyplinowany wojownik czy się, by stać "
                + "niczym kamienny mur. Gdy okuty w zbroję i wyposażony w tarczę "
                + "bohater może przyjąć pozycję obronną, w której staje się o wiele "
                + "trudniejszy do zranienia. Aby zachować pozycję obronną bohater nie"
                + "może się poruszać.");
        PozycjaObronna.addRequiredFeat(Taktyka.getId());
        
        Feat Podciecie = new Feat();
        Podciecie.setName("Podcięcie");
        Podciecie.setDescription("Każdy bohater może podejmowac próbę podcięcia "
                + "oponenta, lecz wykupienie tego atutu oznacza, że jest to "
                + "manewr, który został dobrze wyćwiczony przez bohatera");
        Podciecie.addRequiredFeat(Taktyka.getId());
        
        Feat OkazyjnePodciecie = new Feat();
        OkazyjnePodciecie.setName("Juszalnotpass");
        OkazyjnePodciecie.setDescription("Doświadczony obrońca, poznawszy tę technike, z "
                + "łatwością wykorzystuje długość swej broni, by nie przepuścić wrogów. "
                + "Atut ten powoduje, że każdy kto przebiega obok bohatera w "
                + "pozycji obronnej może zostać podcięty przez niego.");
        OkazyjnePodciecie.addRequiredFeat(PozycjaObronna.getId());
        OkazyjnePodciecie.addRequiredFeat(Podciecie.getId());
                
        Feat WalkaBroniaImprowizowana = new Feat();
        WalkaBroniaImprowizowana.setName("Walka Bronią Improwizowaną");
        WalkaBroniaImprowizowana.setDescription("Bohater zysukuje biegłość w walce bronią "
                + "niekonwencjonalną. Potrafi on posługiwać się podniesionym krzesłem "
                + "równie dobrze, jak szermierz posługuje się mieczem.");
        WalkaBroniaImprowizowana.addRequiredFeat(PodstawyWalki.getId());
        
        Feat TreningZwinnosci = new Feat();
        TreningZwinnosci.setName("Trening Zwinności");
        TreningZwinnosci.setDescription("Nauka tego atutu oznacza, że bohater poświęcił czas "
                + "by zwiększyć swą zwinność w walce i poza nią. Wzięcie go "
                + "daje możliwość zdobycia w przyszłości atutów związanych ze zwinnością.");
        TreningZwinnosci.addRequiredFeat(PodstawyWalki.getId());
        
        Feat PodstawyMagiiMistycznej = new Feat();
        PodstawyMagiiMistycznej.setName("Podstawy Magii Mistycznej");
        PodstawyMagiiMistycznej.addStoryRequirement("Nauka u maga");
        PodstawyMagiiMistycznej.setDescription("Bohater poznaje podstawowe założenia "
                + "działania magii, zyskujac zdolność rzucania najprostszych czarów (sztuczek) "
                + "oraz możliwość zgłębiania tajemnic magii. Dalsze rozwijanie znajomości magii "
                + "wymaga nauki, samodzielnej lub u mentora. (Bohater, który od początku był magiem "
                + "ma ten atut z założenia.)");
        
        Feat ZaawansowanaZnajomoscMagii = new Feat();
        ZaawansowanaZnajomoscMagii.setName("Zaawansowana Znajomość Magii");
        ZaawansowanaZnajomoscMagii.addStoryRequirement("Nauka u maga lub samodzielna");
        ZaawansowanaZnajomoscMagii.addRequiredFeat(PodstawyMagiiMistycznej.getId());
        ZaawansowanaZnajomoscMagii.setDescription("Bohater zyskuje dostęp do większości czarów "
                + "mistycznych. Na tym poziomie może on rzucić maksymalnie 4 czary na dzień "
                + "(kość magii k4).");
        
        Feat WiekszaMocMistyczna = new Feat();
        WiekszaMocMistyczna.setName("Zwiększenie Mocy Mistycznej");
        WiekszaMocMistyczna.setDescription("Bohater trenuje swój umysł i ciało by "
                + "móc wytrzymać przepływ większej ilości many. Wzięcie tego atutu zwiększa "
                + "kości magii (np. z k4 na k6). Atut ten można brac wiele razy, by maksymalnie "
                + "zwiększyć kość czarów do k12.");
        WiekszaMocMistyczna.addRequiredFeat(ZaawansowanaZnajomoscMagii.getId());
        
        Feat DodatkoweCzaryMistyczne = new Feat();
        DodatkoweCzaryMistyczne.setName("Dodatkowy Czar Mistyczny");
        DodatkoweCzaryMistyczne.setDescription("Osoba biegła w arkanach magii potrafi rzucać "
                + "czary bez przemęczania ciała. Biorąc ten atut mag może rzucić jeden "
                + "dodatkowy czar bez zwiększania poziomu trudności czarowania na kości. Wciąż "
                + "musi wykonać rzut. Atut ten można wziąć wiele razy, lecz nie więcej niż 3. "
                + "Aby móc wziąć ten atut bohater <u>musi posiadać kość czarów k12</u>.");
        DodatkoweCzaryMistyczne.addRequiredFeat(WiekszaMocMistyczna.getId());
        
        Feat Skupienie = new Feat();
        Skupienie.setName("Skupienie");
        Skupienie.setDescription("Mag trenuje siłe woli, by nauczyć się głębokiego "
                + "skupienia podczas rzucania czarów. Dzięki temu o wiele łatwiej "
                + "rzucić mu czar nawet w trudnej sytuacji i/lub stresującej "
                + "sytuacji. Nauka skupienia jest również ważnym krokiem do "
                + "nauczenia się wielu trudniejszych sztuk.");
        Skupienie.addRequiredFeat(ZaawansowanaZnajomoscMagii.getId());
        Skupienie.addStoryRequirement("Trening medytacji");
        
        Feat MagiaWWalce = new Feat();
        MagiaWWalce.setName("Magia w walce");
        MagiaWWalce.setDescription("Bohater opanowuje trudną sztukę czarowania w "
                + "walce. Trening maga bojowego jest surowy, jednak pozwala "
                + "ona na jednoczesne czarowanie i obronę podczas walki w "
                + "zwarciu.");
        MagiaWWalce.addRequiredFeat(PodstawyWalki.getId());
        MagiaWWalce.addRequiredFeat(Skupienie.getId());
        MagiaWWalce.addStoryRequirement("Trening na własną rękę lub u nauczyciela");
        
        Feat SzeptaneZaklecie = new Feat();
        SzeptaneZaklecie.setName("Szeptane Zaklęcie");
        SzeptaneZaklecie.setDescription("Magowi, który potrafi skupić swą moc "
                + "wystarczą słabsze komponenty, by rzucić czar. Z tym atutem "
                + "bohater może rzucić czar wypowiadając zaklęcie szeptem. Wciąż "
                + "jednak musi wykonać odpowiednie gesty.");
        SzeptaneZaklecie.addRequiredFeat(Skupienie.getId());
        
        Feat ZaklecieBezSlow = new Feat();
        ZaklecieBezSlow.setName("Zaklęcie Bez Slów");
        ZaklecieBezSlow.setDescription("Chociaż jest to trudna sztuka, mag może "
                + "nauczyć się rzucać swe czary bez komponentu werbalnego. Z "
                + "tym atutem bohater może rzucić czar bez wypowiadania "
                + "słów zaklęcia.");
        ZaklecieBezSlow.addRequiredFeat(SzeptaneZaklecie.getId());
        
        Feat DyskretnaGestykulacja = new Feat();
        DyskretnaGestykulacja.setName("Dyskretne Gesty");
        DyskretnaGestykulacja.setDescription("Skupiony mag może rzucać zaklęcie "
                + "z mniej widocznym komponentem somatycznym. Z tym atutem "
                + "bohater może wykonywać gesty potrzebne do zaklęć mistycznych "
                + "samymi dłońmi. Wciąż jednak musi wypowiedzieć słowa czaru.");
        DyskretnaGestykulacja.addRequiredFeat(Skupienie.getId());
        
        Feat ZaklecieBezGestow = new Feat();
        ZaklecieBezGestow.setName("Zaklęcie Bez Gestów");
        ZaklecieBezGestow.setDescription("Wykupując ten atut bohater uczy się "
                + "sztuki rzucania zaklęć bez gestów. Pozwala to rzucić czar"
                + "mistyczny bez najmniejszego ruchu dłoni.");
        ZaklecieBezGestow.addRequiredFeat(DyskretnaGestykulacja.getId());
        
        Feat PodstawyMagiiKaplanskiej = new Feat();
        PodstawyMagiiKaplanskiej.setName("Podstawy Magii Kapłańskiej");
        PodstawyMagiiKaplanskiej.setDescription("Bohater o głębokiej wierze poznaje "
                + "nauki swego boga, by móc czerpac z jego mocy. Jest to podstawa do "
                + "poznania tajemnicy wzywania boskiej mocy. Poza tym bohater zyskuje możliwość "
                + "rzucania najprostszych kapłańskich sztuczek. (Bohater który od "
                + "początku był kapłanem posiada ten atut z założenia)");
        PodstawyMagiiKaplanskiej.addStoryRequirement("Prawdziwa Wiara w wybranego Boga");
        PodstawyMagiiKaplanskiej.addStoryRequirement("Nauka u kapłana lub na własną rękę");
        
        Feat ZaawansowanaMagiaKaplanska = new Feat();
        ZaawansowanaMagiaKaplanska.setName("Zaawansowana Magia Kapłańska");
        ZaawansowanaMagiaKaplanska.setDescription("Bohater dogłębnie poznaje zasady "
                + "i prawdy swojego, udowodniając mu swą lojalność. Poprzez modlitwy "
                + "może one rzucać więkzość czarów kapłańskich, do 4 na dzień "
                + "(kość wiary k4).");
        ZaawansowanaMagiaKaplanska.addRequiredFeat(PodstawyMagiiKaplanskiej.getId());
        ZaawansowanaMagiaKaplanska.addStoryRequirement("Nauka u kapłana lub na własną rękę");
        
        Feat WiekszaMocKaplanska = new Feat();
        WiekszaMocKaplanska.setName("Większa Moc Kapłańska");
        WiekszaMocKaplanska.setDescription("Bohater będąc oddanym sługa swego boga "
                + "zostaje obdarzony większą mocą, co pozwala mu na rzucanie "
                + "większej ilości czarów dziennie. Atut ten zwiększa kość "
                + "czarów kapłana (np. z k4 na k6). Atut ten można wziąć do 3 "
                + "razy, by maksymalnie uzyskać kośc czarów k12.");
        WiekszaMocKaplanska.addRequiredFeat(ZaawansowanaMagiaKaplanska.getId());
        
        Feat SzybkieDobywanie = new Feat();
        SzybkieDobywanie.setName("Szybkie Dobywanie");
        SzybkieDobywanie.setDescription("Bohater nauczył się dobywać swej broni z "
                + "ogromną zwinnością, co pozwala mu zaskoczyć przeciwnika nagłym jej wyjęciem. "
                + "Atut ten dotyczy jedynie broni białej.");
        SzybkieDobywanie.addRequiredFeat(PodstawyWalki.getId());
        
        Feat AtakZDobycia = new Feat();
        AtakZDobycia.setName("Chi Ryu");
        AtakZDobycia.setDescription("Bohater potrafi tak szybko dobyć broni, że "
                + "może zaskoczyć przeciwnika nagłym atakiem. Niezwykła zwinność "
                + "pozwala mu na zaatakowanie schowaną bronią tak, jakby cały czas "
                + "miał ją w ręku.");
        AtakZDobycia.addRequiredFeat(SzybkieDobywanie.getId());
        AtakZDobycia.addRequiredFeat(TreningZwinnosci.getId());
        
        Feat Gniew = new Feat();
        Gniew.setName("Wewnętrzny Gniew");
        Gniew.setDescription("Wojownik uczy się wywoływać w sobie gniew. "
                + "Podczas walki może atakować niczym dzikie zwierzę, zastraszając "
                + "słabszych wrogów i wyprowadzając silniejsze ciosy, zwracając jdnak "
                + "mniejszą uwagę na unikanie ciosów. Zdolność ta może się też przydać "
                + "podczas zastraszania, gdyż groźby będą wyjątkowo... prawdziwe.");
        Gniew.addRequiredFeat(PodstawyWalki.getId());
        
        Feat Berserk = new Feat();
        Berserk.setName("Szał Berserkera");
        Berserk.setDescription("Wojownik potrafi wprowadzić się w przerażający trans, "
                + "podczas którego walczy jak bestia. W szale bohater kompletnie nie odczuwa "
                + "swych ran (co nie znaczy, że nie może zginąć) i atakuje ze "
                + "zdwojoną siłą, zapominając jednak całkowicie o obronie. Szał"
                + "powoduje wycieńczenie organizmu i po jego zakończenie bohater jest"
                + "bardzo zmęczony.");
        Berserk.addRequiredFeat(Gniew.getId());
        
        Feat Unik = new Feat();
        Unik.setName("Unik");
        Unik.setDescription("Każdy bohater może unikać, jednak ci, którzy wykupią "
                + "ten atut najczęściej są wojownikami, którzy są zwolennikami "
                + "mobilności. Bohater uczy się bardziej polegać na własnym refleksie"
                + "niż na zbroji i tarczy.");
        Unik.addRequiredFeat(PodstawyWalki.getId());
        
        Feat Ogluszanie = new Feat();
        Ogluszanie.setName("Ogłuszanie");
        Ogluszanie.setDescription("Wykupienie tego atutu oznacza, że bohater "
                + "świetnie opanował zdolność wyprowadzania ciosów nie-zabójczych, "
                + "które w najgorszym razie ogłuszą przeciwnika, bądź inaczej "
                + "unieruchomią.");
        Ogluszanie.addRequiredFeat(Taktyka.getId());
            
        Feat AtakZPrzewrotu = new Feat();
        AtakZPrzewrotu.setName("Atak z przewrotu");
        AtakZPrzewrotu.setDescription("Zwinny bohater potrafi wyprowadzić szybką "
                + "ripostę, gdy <u>uda mu się uniknąć</u> ataku wroga, wykorzystując "
                + "fakt, iż jakaś część jego ciała jest odsłonięta.");
        AtakZPrzewrotu.addRequiredFeat(Unik.getId());
        AtakZPrzewrotu.addRequiredFeat(TreningZwinnosci.getId());
        
        Feat SzybkiStrzal = new Feat();
        SzybkiStrzal.setName("Szybki Strzal");
        SzybkiStrzal.setDescription("Bohater korzystając z łuku potrafi bardzo "
                + "szybko oddać strzał. Podobnie jak przy szybkim wyciąganiu broni, "
                + "bohater może tak szybko wystrzelić strzałę, jakby miał ją cały czas "
                + "w ręku. (Atut ten działa również w przyapdku lekkiej broni miotanej.");
        SzybkiStrzal.addRequiredFeat(TreningZwinnosci.getId());
        
        Feat Gadanina = new Feat();
        Gadanina.setName("Gadanina");
        Gadanina.setDescription("Bohater świetnie odnajduje się wśród prostych osób "
                + "i bez problemu potrafi zdobyć przyjaciół do kufla. Zawsze sprawia "
                + "dobre pierwsze wrażenie i potrafi nakłonić karczmarza, żeby "
                + "powiedział jakie są najświeższe plotki.");
        
        Feat Wycena = new Feat();
        Wycena.setName("Wycena");
        Wycena.setDescription("Bohater posiadający wiedzę o handlu może nauczyć "
                + "się intuicyjnie wyceniać dowolny towar. Opierając się o "
                + "doświadczenie i instynkt kupiecki potrafi stwierdzić ile "
                + "mniej więcej warta jest dana rzecz.");
        Wycena.addRequiredFeat(Wiedza.getId());
        
        Feat Targowanie = new Feat();
        Targowanie.setName("Targowanie się");
        Targowanie.setDescription("Z tym atutem bohater zdobywa nadzwyczajną "
                + "umiejętność targowania się z handlarzami, dzięki czemu "
                + "prawie zawsze dostanie okazyjną ofertę, niezależnie czy "
                + "kupuje czy sprzedaje.");
        Targowanie.addRequiredFeat(Wycena.getId());
        Targowanie.addRequiredFeat(Gadanina.getId());
        
        Feat Etykieta = new Feat();
        Etykieta.setName("Etykieta");
        Etykieta.setDescription("Bohater zna zasady tak zwanego dobrego wychowania, "
                + "zawsze potrafi się ubrać i zachować odpowiednio do sytuacji. Dzieki "
                + "temu łatwiej zyskuje uwagę wysokopostawionych osób.");
        Etykieta.addStoryRequirement("Doświadczenie bądź nauka");
        
        Feat Salonowiec = new Feat();
        Salonowiec.setName("Salonowiec");
        Salonowiec.setDescription("Postać potrafi odnaleźć się wśród wysokopostawionych "
                + "osób i nawiązać z nimi dialog. Warto mieć na uwadzę sferach prócz "
                + "ładnego uśmiechu, trzeba również wiedzieć o czym się mówi, toteż "
                + "ten atut nie wystarczy, by podyskutować z hrabią na temat "
                + "fluktuacji na rynku wełny mineralnej. Jednak do wzięcia tego "
                + "atut nie jest wymagana żadna wiedza.");
        Salonowiec.addRequiredFeat(Etykieta.getId());
        Salonowiec.addRequiredFeat(Gadanina.getId());
        
        Feat SilnyCharakter = new Feat();
        SilnyCharakter.setName("Silny Charackter");
        SilnyCharakter.setDescription("Bohater wykształcił sobie silną osobowość, "
                + "dzięki czemu naturalnie bardziej oddziałowywuje na innych. "
                + "Choć atut ten służy głównie do tego, by odblkować kolejne, "
                + "samo posiadanie tego oznacza, że podczas interakcji z innymi "
                + "postaciami bohater ma większą 'siłę przebicia'.");
        
        Feat Zastraszanie = new Feat();
        Zastraszanie.setName("Zastraszanie");
        Zastraszanie.setDescription("Bohater nauczył się budzić prawdziwy lęk "
                + "w sercach innych. Oczywiście każdy osiłek może zagrozić "
                + "przemocą, jednak umiejętność wymyślania najbardziej "
                + "skutecznych gróźb do sytuacji jest prawdziwą sztuką. Z "
                + "tym atutem groźby bohater są wiarygodne, nawet jeśli "
                + "jego pozycja nie pozwala mu ich zrealizować.");
        Zastraszanie.addRequiredFeat(SilnyCharakter.getId());
        
        Feat Podzegacz = new Feat();
        Podzegacz.setName("Podżegacz");
        Podzegacz.setDescription("Bohater uczy się wykorzystywać siłę swojego "
                + "charakteru, by powodować nagłe zrywy u ludzi. Choć może "
                + "nie być on urodzonym przwódcą, bohater potrafi wywołać bójkę w "
                + "karczmie, wywołac zamieszki w mieście, etc. Jeśli warunki nie "
                + "sprzyjają do wszczęcia ruchu, nawet z tym atutem może być to "
                + "bardzo trudne (innymi słowy nie można wszcząć zamieszek na "
                + "placu, gdzie znajduje się 3 wieśniaków).");
        Podzegacz.addRequiredFeat(Gadanina.getId());
        Podzegacz.addRequiredFeat(SilnyCharakter.getId());
        
        Feat Ukrycie = new Feat();
        Ukrycie.setDescription("Bohater z tym atutem potrafi doskonale wykorzystać środowisko "
                + "by ukryć się gdy pozostaje w bezruchu, niezależenie czy szykuje "
                + "zasadzkę, czy ukrywa się przed wrogiem.");
        Ukrycie.setName("Ukrycie");
        
        Feat Skradanie = new Feat();
        Skradanie.setName("Skradanie");
        Skradanie.setDescription("O ile każdy może próbować 'cicho chodzić', to wzięcie "
                + "tego atutu oznacza, że bohater naprawdę nauczył się stąpać bezszelestnie i "
                + "korzystać z cienia. Bohatera z tym atutem o wiele ciężej zauważyć, gdy "
                + "się skrada. Oczywiście, gdy nosi się zbroję płytową nawet ten "
                + "atut nie pomoże.");
        Skradanie.addRequiredFeat(TreningZwinnosci.getId());
        Skradanie.addRequiredFeat(Ukrycie.getId());
        
        Feat Rzemioslo = new Feat();
        Rzemioslo.setName("Rzemiosło ...");
        Rzemioslo.setDescription("Wzięcie tego atutu oznacza, że bohater potrafi "
                + "dobrze tworzyć jakieś przedmioty. Rzemiosłem może być kowalstwo, "
                + "stolarstwo, warzenie trucizn, struganie, szydełkowanie artystyczne, etc.");
        Rzemioslo.addRequiredFeat(Wiedza.getId());
        
        Feat SztuczkaZLina = new Feat();
        SztuczkaZLina.setName("Sztuczka z liną");
        SztuczkaZLina.setDescription("Umiesz robić zajebistą sztuczkę z liną. "
                + "Nie jest to przydatne w walce, czy przy wspinaczce. W sumie "
                + "do niczego się nie przydaje, ale wygląda zajebiście.");
        
        Feat ZreczneDlonie = new Feat();
        ZreczneDlonie.setName("Zręczne Dłonie");
        ZreczneDlonie.setDescription("Bohater zdobywa bardzo dobrą kordynację "
                + "wzrokowo-ruchową. Może wykonywać czynności wymagające precyzji "
                + "i ostrożności. Atut ten jest podstawą do nauki sposób wykorzystania "
                + "tego talentu.");
        
        Feat ObslugaPulapek = new Feat();
        ObslugaPulapek.setName("Obsługa Pułapek");
        ObslugaPulapek.setDescription("Bohater uczy się jak rozbrajać i "
                + "zastawiać co bardziej skomplikowane pułapki. Z pewnością "
                + "nie trzeba być geniuszem by zrobić wilczy dół, ale ten atut "
                + "pozwala na obsługę, czy nawet konstrukcję pułapek "
                + "wykorzystujących mechanizmy.");
        ObslugaPulapek.addRequiredFeat(ZreczneDlonie.getId());
        ObslugaPulapek.addRequiredFeat(Wiedza.getId());
        
        Feat Finta = new Feat();
        Finta.setName("Finta");
        Finta.setDescription("Każdy wojownik wie czym jest finta, ale dopiero "
                + "po solidnym treningu stają się one proste do wykonania. "
                + "Wzięcie tego atutu oznacza, że bohater trenował finty i "
                + "potrafi je bardzo dobrze wykonywać.");
        Finta.addRequiredFeat(Taktyka.getId());
        Finta.addRequiredFeat(ZreczneDlonie.getId());
        
        Feat ZmyslWalki = new Feat();
        ZmyslWalki.setName("Zmysł walki");
        ZmyslWalki.setDescription("Jedynie prawdziwie doświadczeni wojownicy "
                + "uczą się, że podczas walki nigdy nie można tracić skupienia. "
                + "Z tym atutem bohater zawsze zachowuje czujność podczas walki, "
                + "przede wszystkim w kwestii ataków wyprowadzanych przez "
                + "przeciwnika.");
        ZmyslWalki.addRequiredFeat(Taktyka.getId());
        ZmyslWalki.addStoryRequirement("Doświadczenie w walce");
        
        Feat OtwieranieZamkow = new Feat();
        OtwieranieZamkow.setName("Otwieranie zamków");
        OtwieranieZamkow.setDescription("Bohater uczy się jak sprawnie otwierać "
                + "zamknięte drzwi i skrzynie, do których akurat nie ma kluczy. "
                + "Gdy bohater ma ten atut, zamek musi być albo magiczny, albo "
                + "naprawdę skomplikowany, by nie dało się go otworzyć.");
        OtwieranieZamkow.addRequiredFeat(Wiedza.getId());
        OtwieranieZamkow.addRequiredFeat(ZreczneDlonie.getId());
        
        Feat Rozbrajanie = new Feat();
        Rozbrajanie.setName("Rozbrajanie");
        Rozbrajanie.setDescription("Bohater uczy się manewru, który pozwal mu "
                + "na wytrącanie broni z dłoni przeciwnika. Manewr ten nie ma "
                + "na celu zadanie obrażeń, toteż przeważnie tego nie robi, a "
                + "jedynie pozbawia cel broni. ");
        Rozbrajanie.addRequiredFeat(Taktyka.getId());
        Rozbrajanie.addRequiredFeat(ZreczneDlonie.getId());
        Feat.Save();
        
        Feat TreningSilowy = new Feat();
        TreningSilowy.setName("Trening Siłowy");
        TreningSilowy.setDescription("Czy to z chęci, czy przymusu, bohater przez "
                + "długi czas trenował mięśnie, przez co jest teraz naprawdę silny. "
                + "Jego muskulatura może budzić podziw i strach. Poza oczywistym "
                + "faktem, że pozwala mu po prostu porządnie pierdolnąć.");
        
        Feat WiekszeRozbrojenie = new Feat();
        WiekszeRozbrojenie.setName("Miażdżące Rozbrajanie");
        WiekszeRozbrojenie.setDescription("Silny bohater, który uprzednio "
                + "opanował manewr rozbrajania uczy się teraz wybijać broń "
                + "przeciwnika z taką siłą, że odlatuje na dużą odległość, być "
                + "może nawet zostaje uszkodzona.");
        WiekszeRozbrojenie.addRequiredFeat(Rozbrajanie.getId());
        WiekszeRozbrojenie.addRequiredFeat(TreningSilowy.getId());
        
            
        Feat Torturowanie = new Feat();
        Torturowanie.setName("Torturowanie");
        Torturowanie.setDescription("Wykupienie tego atutu oznacza, że bohater "
                + "nauczył się prawidziwe torturować swoje ofiary, zarówno "
                + "psychicznie jak i fizycznie. Potrafi zadawać bolesne, lecz "
                + "nie zabójcze rany i jednocześnie osłabiać nieszczęśnika "
                + "wywołując u niego strch.");
        Torturowanie.addRequiredFeat(Zastraszanie.getId());
        Torturowanie.addRequiredFeat(Ogluszanie.getId());
        
        Feat.Save();
        
        
        
    }
     
    public static void main(String[] args) {
        //generateData();
        //Feat.Save();
        Feat.Load();
        if(Feat.getFeats().isEmpty()) {
            generateData();
        }
        Collection<Feat> feats = Feat.getFeats().values();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e ){
            e.printStackTrace();
        }
        
        Connection c = null;
        try {
            
            c = DriverManager.getConnection(
                    "jdbc:mysql://famalis.no-ip.biz:3306/rpg","Sergio", "quovadis1");
            
        } catch (Exception e) {
     
            e.printStackTrace();
        }
        
    }
}
