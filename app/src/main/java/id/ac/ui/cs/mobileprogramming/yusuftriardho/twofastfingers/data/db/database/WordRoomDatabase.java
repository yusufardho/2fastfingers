package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.dao.WordDao;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity.Word;


@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();
    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
             new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;
        String[] indonesia_words, english_words;
        PopulateDbAsync(WordRoomDatabase db) {
            english_words = "3 3wQsQIU 3wsd 3wJLI 3wJ4d 3aad2I 3aaJDBQOK 3aaJLOI 3aDJcc 3aI 3aIQJO 3aIQ4QIU 3aIL3ssU 3BB 3BBDdcc 3B5QOQcID3IQJO 3B5QI 3BLsI 3SSdaI 3SIdD 3K3QO 3K3QOcI 3Kd 3KdOaU 3KdOI 3KJ 3KDdd 3KDdd5dOI 3zd3B 3QD 3ss 3ssJe 3s5JcI 3sJOd 3sJOK 3sDd3BU 3scJ 3sIzJLKz 3se3Uc H5dDQa3O 35JOK 35JLOI 3O3sUcQc 3OB 3OQ53s 3OJIzdD 3OcedD 3OU 3OUJOd 3OUIzQOK 322d3D 322sU 322DJ3az 3Dd3 3DKLd 3D5 3DJLOB 3DDQ4d 3DI 3DIQasd 3DIQcI 3c 3cn 3ccL5d 3I 3II3an 3IIdOIQJO 3IIJDOdU 3LBQdOad 3LIzJD 3LIzJDQIU 343Qs3wsd 34JQB 3e3U w3wU w3an w3B w3K w3ss w3On w3D w3cd wd wd3I wd3LIQSLs wda3Lcd wdaJ5d wdB wdSJDd wdKQO wdz34QJD wdzQOB wdsQd4d wdOdSQI wdcI wdIIdD wdIeddO wdUJOB wQK wQss wQssQJO wQI ws3an wsJJB wsLd wJ3DB wJBU wJJn wJDO wJIz wJP wJU wDd3n wDQOK wDJIzdD wLBKdI wLQsB wLQsBQOK wLcQOdcc wLI wLU wU a3ss a35dD3 a3523QKO a3O a3OadD a3OBQB3Id a32QI3s a3D a3DB a3Dd a3DddD a3DDU a3cd a3Iaz a3Lcd adss adOIdD adOID3s adOILDU adDI3QO adDI3QOsU az3QD az3ssdOKd az3Oad az3OKd az3D3aIdD az3DKd azdan azQsB azJQad azJJcd azLDaz aQIQXdO aQIU aQ4Qs as3Q5 as3cc asd3D asd3DsU asJcd aJ3az aJsB aJssdaIQJO aJssdKd aJsJD aJ5d aJ55dDaQ3s aJ55JO aJ55LOQIU aJ523OU aJ523Dd aJ52LIdD aJOadDO aJOBQIQJO aJOSdDdOad yJOKDdcc aJOcQBdD aJOcL5dD aJOI3QO aJOIQOLd aJOIDJs aJcI aJLsB aJLOIDU aJL2sd aJLDcd aJLDI aJ4dD aDd3Id aDQ5d aLsILD3s aLsILDd aL2 aLDDdOI aLcIJ5dD aLI B3Dn B3I3 B3LKzIdD B3U Bd3B Bd3s Bd3Iz Bdw3Id Bda3Bd BdaQBd BdaQcQJO Bdd2 BdSdOcd BdKDdd Gd5JaD3I Bd5JaD3IQa BdcaDQwd BdcQKO Bdc2QId BdI3Qs BdIdD5QOd Bd4dsJ2 Bd4dsJ25dOI BQd BQSSdDdOad BQSSdDdOI BQSSQaLsI BQOOdD BQDdaIQJO BQDdaIJD BQcaJ4dD BQcaLcc BQcaLccQJO BQcd3cd BJ BJaIJD BJK BJJD BJeO BD3e BDd35 BDQ4d BDJ2 BDLK BLDQOK d3az d3DsU d3cI d3cU d3I daJOJ5Qa daJOJ5U dBKd dBLa3IQJO dSSdaI dSSJDI dQKzI dQIzdD dsdaIQJO dscd d52sJUdd dOB dOdDKU dORJU dOJLKz dOIdD dOIQDd dO4QDJO5dOI dO4QDJO5dOI3s dc2daQ3ssU dcI3wsQcz d4dO d4dOQOK d4dOI d4dD d4dDU d4dDUwJBU d4dDUJOd d4dDUIzQOK d4QBdOad dP3aIsU dP352sd dPdaLIQ4d dPQcI dP2daI dP2dDQdOad dP2dDI dP2s3QO dUd S3ad S3aI S3aIJD S3Qs S3ss S35QsU S3D S3cI S3IzdD Sd3D SdBdD3s Sdds SddsQOK Sde SQdsB SQKzI SQKLDd SQss SQs5 SQO3s SQO3ssU SQO3OaQ3s SQOB SQOd SQOKdD SQOQcz SQDd SQD5 SQDcI SQcz SQ4d SsJJD SsU SJaLc SJssJe SJJB SJJI SJD SJDad SJDdQKO SJDKdI SJD5 SJD5dD SJDe3DB SJLD SDdd SDQdOB SDJ5 SDJOI SLss SLOB SLILDd K35d K3DBdO K3c KdOdD3s KdOdD3IQJO KdI KQDs KQ4d Ks3cc KJ KJ3s KJJB KJ4dDO5dOI KDd3I KDddO KDJLOB KDJL2 KDJe KDJeIz KLdcc KLO KLU z3QD z3sS z3OB z3OK z322dO z322U z3DB z34d zd zd3B zd3sIz zd3D zd3DI zd3I zd34U zds2 zdD zdDd zdDcdsS zQKz zQ5 zQ5cdsS zQc zQcIJDU zQI zJsB zJ5d zJ2d zJc2QI3s zJI zJIds zJLD zJLcd zJe zJed4dD zLKd zL53O zLOBDdB zLcw3OB g QBd3 QBdOIQSU QS Q53Kd Q53KQOd Q523aI Q52JDI3OI Q52DJ4d QO QOasLBd QOasLBQOK QOaDd3cd QOBddB QOBQa3Id QOBQ4QBL3s QOBLcIDU QOSJD53IQJO QOcQBd QOcId3B QOcIQILIQJO QOIdDdcI QOIdDdcIQOK QOIdDO3IQJO3s QOIdD4Qde QOIJ QO4dcI5dOI QO4Js4d QccLd QI QId5 QIc QIcdsS RJw RJQO RLcI ndd2 ndU nQB nQss nQOB nQIazdO nOJe nOJesdBKd s3OB s3OKL3Kd s3DKd s3cI s3Id s3IdD s3LKz s3e s3eUdD s3U sd3B sd3BdD sd3DO sd3cI sd34d sdSI sdK sdK3s sdcc sdI sdIIdD sd4ds sQd sQSd sQKzI sQnd sQndsU sQOd sQcI sQcIdO sQIIsd sQ4d sJa3s sJOK sJJn sJcd sJcc sJI sJ4d sJe 53azQOd 53K3XQOd 53QO 53QOI3QO 53RJD 53RJDQIU 53nd 53O 53O3Kd 53O3Kd5dOI 53O3KdD 53OU 53DndI 53DDQ3Kd 53IdDQ3s 53IIdD 53U 53Uwd 5d 5d3O 5d3cLDd 5dBQ3 5dBQa3s 5ddI 5ddIQOK 5d5wdD 5d5JDU 5dOIQJO 5dcc3Kd 5dIzJB 5QBBsd 5QKzI 5QsQI3DU 5QssQJO 5QOB 5QOLId 5Qcc 5QccQJO 5JBds 5JBdDO 5J5dOI 5JOdU 5JOIz 5JDd 5JDOQOK 5JcI 5JIzdD 5JLIz 5J4d 5J4d5dOI 5J4Qd TD TDc 5Laz 5LcQa 5LcI 5U 5UcdsS O35d O3IQJO O3IQJO3s O3ILD3s O3ILDd Od3D Od3DsU Odadcc3DU OddB OdIeJDn Od4dD Ode Odec Odec232dD OdPI OQad OQKzI OJ OJOd OJD OJDIz OJI OJId OJIzQOK OJIQad OJe a3O'I OL5wdD JaaLD JS JSS JSSdD JSSQad JSSQadD JSSQaQ3s JSIdO Jz JQs Jn JsB JO JOad JOd JOsU JOIJ J2dO J2dD3IQJO J22JDILOQIU J2IQJO JD JDBdD JDK3OQX3IQJO JIzdD JIzdDc JLD JLI JLIcQBd J4dD JeO JeOdD 23Kd 23QO 23QOIQOK 232dD 23DdOI 23DI 23DIQaQ23OI 23DIQaLs3D 23DIQaLs3DsU 23DIOdD 23DIU 23cc 23cI 23IQdOI 23IIdDO 23U 2d3ad 2dJ2sd 2dD 2dDSJD5 2dDSJD53Oad 2dDz32c 2dDQJB 2dDcJO 2dDcJO3s 2zJOd 2zUcQa3s 2Qan 2QaILDd 2Qdad 2s3ad 2s3O 2s3OI 2s3U 2s3UdD tT 2JQOI 2JsQad 2JsQaU 2JsQIQa3s 2JsQIQac 2JJD 2J2Ls3D 2J2Ls3IQJO 2JcQIQJO 2JcQIQ4d 2JccQwsd 2JedD 2D3aIQad 2Dd23Dd 2DdcdOI 2DdcQBdOI 2DdccLDd 2DdIIU 2Dd4dOI 2DQad 2DQ43Id 2DJw3wsU 2DJwsd5 2DJadcc 2DJBLad 2DJBLaI 2DJBLaIQJO 2DJSdccQJO3s 2DJSdccJD 2DJKD35 2DJRdaI 2DJ2dDIU 2DJIdaI 2DJ4d 2DJ4QBd 2LwsQa 2Lss 2LD2Jcd 2Lcz 2LI fL3sQIU fLdcIQJO fLQansU fLQId D3ad D3BQJ D3Qcd D3OKd D3Id D3IzdD Dd3az Dd3B Dd3BU Dd3s Dd3sQIU Dd3sQXd Dd3ssU Dd3cJO DdadQ4d DdadOI DdadOIsU DdaJKOQXd DdaJDB DdB DdBLad DdSsdaI DdKQJO Dds3Id Dds3IQJOczQ2 DdsQKQJLc Dd53QO Dd5d5wdD Dd5J4d Dd2JDI Dd2DdcdOI 9d2LwsQa3O DdfLQDd Ddcd3Daz DdcJLDad Ddc2JOB Ddc2JOcd Ddc2JOcQwQsQIU DdcI DdcLsI DdILDO Dd4d3s DQaz DQKzI DQcd DQcn DJ3B DJan DJsd DJJ5 DLsd DLO c3Sd c35d c34d c3U cadOd cazJJs caQdOad caQdOIQcI caJDd cd3 cd3cJO cd3I cdaJOB cdaIQJO cdaLDQIU cdd cddn cdd5 cdss cdOB cdOQJD cdOcd cdDQdc cdDQJLc cdD4d cdD4Qad cdI cd4dO cd4dD3s cdP cdPL3s cz3nd cz3Dd czd czJJI czJDI czJI czJLsB czJLsBdD czJe cQBd cQKO cQKOQSQa3OI cQ5Qs3D cQ52sd cQ52sU cQOad cQOK cQOKsd cQcIdD cQI cQId cQIL3IQJO cQP cQXd cnQss cnQO c53ss c5Qsd cJ cJaQ3s cJaQdIU cJsBQdD cJ5d cJ5dwJBU cJ5dJOd cJ5dIzQOK cJ5dIQ5dc cJO cJOK cJJO cJDI cJLOB cJLDad cJLIz cJLIzdDO c23ad c2d3n c2daQ3s c2daQSQa c2ddaz c2dOB c2JDI c2DQOK cI3SS cI3Kd cI3OB cI3OB3DB cI3D cI3DI cI3Id cI3Id5dOI cI3IQJO cI3U cId2 cIQss cIJan cIJ2 cIJDd cIJDU cID3IdKU cIDddI cIDJOK cIDLaILDd cILBdOI cILBU cILSS cIUsd cLwRdaI cLaadcc cLaadccSLs cLaz cLBBdOsU cLSSdD cLKKdcI cL55dD cL22JDI cLDd cLDS3ad cUcId5 I3wsd I3nd I3sn I3cn I3P Id3az Id3azdD Id35 IdazOJsJKU Idsd4QcQJO Idss IdO IdOB IdD5 IdcI Iz3O Iz3On Iz3I Izd IzdQD Izd5 Izd5cds4dc IzdO IzdJDU IzdDd Izdcd IzdU IzQOK IzQOn IzQDB IzQc IzJcd IzJLKz IzJLKzI IzJLc3OB IzDd3I IzDdd IzDJLKz IzDJLKzJLI IzDJe IzLc IQ5d IJ IJB3U IJKdIzdD IJOQKzI IJJ IJ2 IJI3s IJLKz IJe3DB IJeO ID3Bd ID3BQIQJO3s ID3QOQOK ID34ds IDd3I IDd3I5dOI IDdd IDQ3s IDQ2 IDJLwsd IDLd IDLIz IDU ILDO AE IeJ IU2d LOBdD LOBdDcI3OB LOQI LOIQs L2 L2JO Lc Lcd LcL3ssU 43sLd 43DQJLc 4dDU 4QaIQ5 4Qde 4QJsdOad 4QcQI 4JQad 4JId e3QI e3sn e3ss e3OI e3D e3Iaz e3IdD e3U ed ed32JO ed3D eddn edQKzI edss edcI edcIdDO ez3I ez3Id4dD ezdO ezdDd ezdIzdD ezQaz ezQsd ezQId ezJ ezJsd ezJ5 ezJcd ezU eQBd eQSd eQss eQO eQOB eQOBJe eQcz eQIz eQIzQO eQIzJLI eJ53O eJOBdD eJDB eJDn eJDndD eJDsB eJDDU eJLsB eDQId eDQIdD eDJOK U3DB Ud3z Ud3D Udc UdI UJL UJLOK UJLD UJLDcdsS v3anIJwdDSdcI"
                    .split(" ");
            indonesia_words = "3w3B 5d5dDQnc3 3wL-3wL 5d5QsQz 3B3 5d5QsQnQ 3B3s3z 5d5Q52QO 3BQs 5d5QOI3 HSDQn3 5d5JIJOK 3K3n 5d52dOK3DLzQ HKLcILc 5d52dDw3QnQ 3QD 5d52dDQOK3In3O 53I3 5d52dDcQ32n3O 3n3O 5d52dDIQ5w3OKn3O 3n3D 5d5LnLs 3nzQD 5d5LILcn3O 3nzQDOU3 5dO3Qnn3O 3nD3w 5dO3nLIQ 3nIQS 5dO35w3zn3O 3nIQ4QI3c 5dO3OK 3s35 5dO3OK3OQ cd5dcI3 5dO3OKKLOK 3s3c3O 5dO3OKQc Hs3cn3 5dO3OKn32 3s3I 5dO3D3 3sS3wdI 5dO3DQn 3sQD3O 5dO3I32 353O 5dO3e3Dn3O H5dDQn3 5dOa352LD 3O3n 5dOa323Q s3nQ-s3nQ 5dOa3I3I 3O3n-3O3n 5dOadK3z 3OB3 5dOaJw3 3Odz 5dOB3s35 3OKKJI3 5dOB323I 3OKQO 5dOB323In3O 3OKn3 5dOBdOK3D 3OKn3I 5dOBdOK3Dn3O 3OKnLI3O 5dOBQDQn3O 3OKc3 5dOBLnLOK 3ORQOK 5dOd5w3n 3OI3D3 5dOd523In3O 323 5dOd5Ln3O 323n3z 5dOdOILn3O 3232LO 5dOdDQ53 323DId5dO 5dOdI32 32ds 5dOK3aL 32Q 5dOK3R3D H2DQs 5dOK3s3zn3O 3D3z 5dOK3sQD 3DIQnds 5dOK353IQ 3DLc 5dOK35w3OK 3c3s 5dOK35wQs 3c32 5dOK3OBLOK HcQ3 5dOK3OKKLn 3cQOK 5dOK323 3csQ 5dOK3I3n3O 3I32 5dOK3ILD 3I3c 5dOK3ULOn3O 3I3L 5dOKdR3 3IJ5 5dOKdnc2DdcQn3O 3ILD3O 5dOKd5w3OKn3O HLcID3sQ3 TdOKdO3n3O 3e3n n323s 5dOKdO3sQ 3e3s 5dOKdDQn3O 3e35 5dOKK3wLOKn3O 3e3O 5dOKK3sQ 3U3z 5dOKK35w3D 3U35 5dOKK35w3Dn3O 3U3I 5dOKK3OIQn3O 3ULO3O 5dOKK3OILOK w3w 5dOKK3DQcw3e3zQ w3wQ 5dOKKJU3OK w3B3Q 5dOKKLO3n3O w3K3Q53O3 5dOKz3wQcn3O w3K3cQ 5dOKz3B32Q w3KQ3O 5dOKz32Lc x3KQn3O 5dOKz3D32n3O w3KLc 5dOKz3cQsn3O w3z3O 5dOKzdD3On3O w3n3D 5dOKzQs3OK w3z3c3 5dOKzQOB3DQ w3z3U3 5dOKzQILOK w3zn3O 5dOKzJD53IQ w3zL 5dOKQnLIQ w3ze3 5dOKQD3 w3Qn 5dOKQDQ5 w3R3 5dOKQcQ w3s3Q 5dOKQXQOn3O w3sJO 5dOKL3c3Q w3OKK3 5dOKLw3z w3OKc3 5dOKLnLD w3OKLO3O 5dOKL52Lsn3O w3On 5dOKL5L5n3O w3OIL3O 5dOKLc32 w3OU3n 5dOQn3z w323n 5dOQn53IQ w3D 5dOQ53OK w3D3I 5dOQ5w3OK w3DQc 5dOQOKK3sn3O w3DL 5dOQOKn3In3O w3c3z 5dOQ2L w3cdw3ss 5dOQI w3I3 5dOQL2 w3I3OK 5dOR3w3I3c 5dOR3K3 w3IQO 5dOR3s3On3O w3IL 5dOR3ILzn3O w3D3 5dOR3e3w w3L 5dORds3R3z w3U3OK3O 5dORds3cn3O w3UQ 5dORL3s wd3 aLn3Q 5dOJs3n wdw3O 5dOJOIJO wdwdn 5dOI3z wdwdD323 5dOIdK3 wdn3c 5dOL3OKn3O wdnL 5dOLRL wds 5dOLsQc wds3R3D 5dOLOKKL wds3n3OK 5dOLORLnn3O xds3OB3 5dOLDLOn3O wdsL5 5dOLDLI wdO3OK 5dOLIL2 wdO3D 5dOU3B3DQ wdOB3 5dOU3nQIQ wdOBdD3 5dOU3OUQ wdOKnJn 5dOU3OUQn3O wdOQz 5dOU32L wdOcQO 5dOU3D3On3O wdOIdOK 5dOU3I3n3O wdOILn 5dOUdwdD3OK wdOL3 5dOUd5wLOUQn3O wdD3OB3 5dOUdOILz wdD3OQ TdOUdDJwJI wdD3DIQ 5dOUQ523O wdD3c 5dD3z wdD3I 5dD3Qz wdDw3K3Q 5dD3c3 wdDw3z3U3 5dDdK3OK wdDw3IL-w3IL 5dDdn3 wdDwdB3 5dcQO wdDwQa3D3 TdcQD wdDwQcQn 5dcnQ2LO wdDwLDL 5dIJBd wdDBQDQ 5de3nQsQ wdDdO3OK 5Qs wdDdOa3O3 5QsQn wdDK3wLOK 5QsQIdD wdDKdD3n 5QOdD3s wdDKLO3 5QOKKL wdDz3D32 5QOL53O wdDz3DK3 5QOU3n wdDz3cQs 5QDQ2 wdDzdOIQ 5QcQ wdDQnLIOU3 5QcnQO wdDQI3 5JwQs wdDR3s3O 5JB3s wdDR3ORQ 5JBds wdDRL3OK 5JBdDO wdDnd23s3 5JOUdI wdDs3DQ 5JIJD wdDs3U3D 5LB3 wdDsQOBLOK 5LB3z wdD53QO 5Ls3Q wdD53OS33I 5LsLI wdD5Q52Q 5LOKQs wdDO3S3c 5LOKnQO wdDOQs3Q 5LDQB wdDJOKK3 5LDOQ wdD2QnQD 5LcQn wdD2LI3D 5LcQn3s wdDc353 5LcQ5 wdDcd53OK3I BQOKQO wdDcQz TLcQ5 KLKLD wdDcQO3D 23O3c wdDI3z32 cd5Q wdDI3OU3-I3OU3 5LcI3zQs wdDId5L 5LcLz wdDIQOB3n O3B3 wdDLOILOK O3S3c wdc3D O3Qn wdcQ O353 wdcJn O35LO wQ3c3 O3cQz3I wQ3c3OU3 O3cQJO3s wQ3U3 W3I3s xQwQ O3LOK3O wQwQD OdK3D3 wQB3OK OdK3IQS wQR3nc3O3 WdKDJ wQOKn3Q OdOdn wQOI3OK OQs3Q wQDL OJs wQc OU3 wQc3 OU353O wQcOQc OU3DQOK wsJn OU3I3 wJBJz Jw3I wJzJOK JwUdn wJs3 JncQKdO wJOdn3 NnIJwdD wJIJs Ns3zD3K3 wL3z Jsdz wLwLn n3DdO3 QIL wLB3n J2dD3cQ xLnQI JD3OK wLnIQ wLnL JDwQI wLs3O JDK3OQc3cQ wLs3I JI3n wLsL JIJI wL5Q 23wDQn wLOK3 23B3I wLDL-wLDL 23K3D wLDLn 23KQ wLDLOK 23z35 wLcLD 23Q wLI3 23R3n wLIQD 23n a3w3OK 23n3Q3O a3z3U3 23ndI a3QD 23sQOK a352LD3O cdBQnQI a3OKnQD IQOKKQ a3D3 2353O a3I 23O3z a3I3I3O ad53c 23OaQ ad23I t3OB3Q adD3z 23OB3OK3O adDQI3 23OBL3O adD5QO 23OKKQs3O yQO3 23OR3OK aQOI3 23OR3OKOU3 aQDQ 23OI3Q aJns3I 2323O aJOIJz 23D3 2dOJOIJO aL3a3 23D3sds aLaQ t3DQc aLnL2 23DI3Q aLD35 23DIQnds B3B3 23DL-23DL B3dD3z 23c3OK B3SI3D 23c3OK3O B3KQOK 23c3D B3s35 t3cQSQn B3O 23cQD s3QO-s3QO 23cIQ B3O3L 23cLn3O B3OKn3s 23Iz B32LD 2da3z B3D3z 2dB3s353O B3DQ 2dBLsQ B3c3D 2dndDR3 B3cQ 2dndDR33O B3I3OK 2ds3wLz3O B3I3D 2ds3R3D3O B3LO 2ds3O3 BdwL 2ds3O-2ds3O BdSQOQcQ 2ds3IQz Bdn3I 2ds3U3D3O Bds323O 2dsLQI Bd5QnQ3O 2d5w3a3 BdOK3O 2d5wdDQ3O BdOKnQ 2d5wdDQI3zL3O Bd23O 2d5wLsLz Bdc3 2d5wLDL Bde3c3 2d5dDQOI3z 2d5QsQn 2d5Q52QO w3e3z 2d5JKJn3O 2d5JIJOK3O 2d5LB3 sL3D 2d5LnQ5 53O32LO 2dO3 c352QOK 2dO352Qs3O c3O3 2dO3DQn3O cdsLDLz 2dO3c3D3O cQOQ 2dOa3DQ3O BQ3 2dOB3nQ3O BQ3B3n3O 2dOB323I BQ3KD35 2dOB323I3O BQ3R3Dn3O 2dOBdn BQ35 2dOBdOK3D3O BQ35wQs 2dOBQBQn3O BQ35-BQ35 2dOdsQIQ3O BQ35dIdD 2dOd5L3O BQw3OKLO 2dOdDw3OK3O BQw3U3D 2dOK3BQs3O BQwdDQn3O 2dOK3s353O BQwLOKnLc 2dOK3DLz BQwLILzn3O 2dOK3ILD3O BQa3QDn3O 2dOKd5w3OK3O BQa3I3I 2dOKdI3zL3O BQBJDJOK 2dOKK3DQc BQK35w3Dn3O 2dOKKdD3n BQndI3zLQ 2dOKKLO33O BQs3nLn3O 2dOKsQz3I3O BQsd523Dn3O 2dOKLORLOK BQ53n3O 2dOR3K3 BQ53O3 2dORds3c3O 53O3 2dORL3s3O BQOBQOK 2dOcQs 2dOIQOK BQJw3IQ 2dOIQOKOU3 BQ2dDndO3sn3O 2dOLz BQDQ 2dOLsQc BQc3QO 2dOLsQc3O BQcQ523O 2dOLIL2 BQcnLcQ 2dOU3QD BQI352Qsn3O 2dOU3nQI BQI3OKn32 2dOUdw3w BQI3DQn 2dOUdw3D3O BQId5Ln3O 2dOULcLO3O BQ4QcQ 2dD BQU3nQOQ 2dD3zL BJnIdD 2dD3n BJs3D 2dD3s3I3O BJ5w3 tdD3OaQc BJOKnD3n 2dD3OK BJDJOK3O 2dD3OKn32 BJI 2dD32Q3O BDde 2dDwdB33O BL3 2dDa3n323O n3sQ 2dDB3K3OK3O sQ23I 2dDB353Q3O BL3wds3c 2dDd523I BL32LsLz 2dDd52L3O BLBLn 2dDdOa3O33O BLOQ3 2dDKQ dSdn 2dDKLDL3O dnJD 2dDz3IQ3O dnc2dDQ5dO 2dDQOaQ3O dnc2DdcQ 2dDQOI3z dncID3 2dDQJBd dsd5dO 2dDQcIQe3 d53c 2dDR3s3O3O d523I 2dDRL3OK3O d523I2LsLz 2dDnds3zQ3O dO35 2dDsQOBLOK3O dOdDKQ 2dDsL dOKn3L 2dD53QO3O dc 2dD5Ln33O S3R3D 2dDO3S3c3O S3nI3 2dDO3z S3nIJD 2dDOU3I33O S34JDQI 2dD2LcI3n33O SQs5 2dDcdBQ33O SQcQn 2dDcdKQ SD3c3 2dDcdO SLOKcQ 2dDcQc K3BQc 2dDI353 K3K3s 2dDI3OU33O K3Qw 2dDI3DLOK3O K3R3z 2dDId52LD3O K35w3D 2dDILn3D3O K3OBL5 2dDIL5wLz3O K3D35 2dDILORLn3O K3D3cQ 2dDLw3z3O K3DQc 2dDLc3z33O K3c 2dDLI K3LO 2dc3O3O K3U3 2dc3e3I Kds32 IdDw3OK KdsJ5w3OK 2dI3 Kd5wQD3 2dI3OQ KdD3n3O 2dIQD KdDw3OK 2dIdDO3n3O KdDdR3 2dIL3s3OK3O KdDK3RQ 2dILK3c KdDJw3n tzQs3Bds2zQ3 KQKQ 2Q3OJ KQKQI3O 2QB3IJ KQs3 2QR3I KQsQD3O 2QnQD KJOa3OK3O 2QnQD3O KD3SQn 2QsQz3O KD3IQc 2QsJI KL3 2QOIL KLaQ 2QOLc KLKL2 2Q23 KLs3 2QDQOK KLsLOK3O 2Qc3L KLOLOK 2QcIJs MLD3L3O 2QI3 KLDL 2s3OQI KLDLO 2s3cIQn z3BQ3z 2sLc z3nQ5 2JzJO z3s353O 2Js3 z3sJ 2JsQcQ z3sLc 2JsQIQn z352QD 2JsJc IQB3n 2JO z3OK3I 2JOQ z3OU3 2J2Ls3cQ z3DK3 2J2LsdD z3DQ 2Jc QOQ 2JcQcQ z3DLc 2JcQIQS z3cQs 2JI z3IQ-z3IQ 2JIJOK3O zdw3I 2D3nIdn zdnI3D 2D3nIQc zde3O tD3OaQc v3anIJwdDSdcI"
                    .split(" ");
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();

            for (int i = 0; i < Math.min(english_words.length, indonesia_words.length); i ++) {
               mDao.insert(new Word(english_words[i], indonesia_words[i]));
            }

            return null;
        }
    }
}