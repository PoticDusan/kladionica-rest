package rs.ac.metropolitan.kladionicarest.config;

public class Path {

    public static final String API = "/api";

    private static final String SEPARATOR = "/";
    private static final String ID = "{id}";

    // Swagger
    public static final String API_DOCS = "/v2/api-docs";
    public static final String SWAGGER = "/swagger*/**";
    public static final String WEBJARS = "/webjars/**";
    public static final String CONFIGURATION = "/configuration/**";

    // Auth
    public static final String AUTH = "/auth";
    public static final String REGISTER = "/register";
    public static final String LOGOUT = "/logout";

    public static final String API_AUTH = API + AUTH;
    public static final String API_REGISTER = API + REGISTER;

    // User
    public static final String USERS = "/users";
    private static final String USER = "/user";
    public static final String USER_ID = USER + SEPARATOR + ID;
    public static final String USER_PASSWORD = USER + SEPARATOR + "password";

    //BetOdd
    public static final String BETODDS = "/betOdds";
    public static final String BETODD = "/betOdd";
    public static final String BETODD_ID = BETODD + SEPARATOR + "{betOddId}";

    //Transaction
    public static final String TRANSACTIONS = "/transactions";
    public static final String TRANSACTION = "/transaction";
    public static final String TRANSACTION_ID = TRANSACTION + SEPARATOR + "{transactionId}";

    //Wallet
    public static final String WALLETS = "/wallets";
    public static final String WALLET = "/wallet";
    public static final String WALLET_ID = WALLET + SEPARATOR + "{walletId}";
    public static final String WALLETTRANSACTION = WALLET_ID + TRANSACTION;


    //Match
    public static final String MATCHES = "/matches";
    public static final String MATCH = "/match";
    public static final String MATCH_ID = MATCH + SEPARATOR + "{matchId}";


    //MATCHTEAM
    public static final String MATCHTEAMS = "/matchTeams";
    public static final String MATCHTEAM = "/matchTeam";
    public static final String MATCHTEAM_ID = MATCHTEAM + SEPARATOR + "{matchId}";

    //Team
    public static final String TEAMS = "/teams";
    public static final String TEAM = "/team";
    public static final String TEAM_ID = TEAM + SEPARATOR + "{teamId}";
    public static final String TEAMMATCHES = TEAM_ID + MATCHTEAM;
}