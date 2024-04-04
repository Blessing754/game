package main;

import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;

    }
    public void setObject(){
        gp.obj[0] =new OBJ_Key();
        gp.obj[0].worldX= 8 * gp.tileSize;
        gp.obj[0].worldY= 7 * gp.tileSize;

        gp.obj[1] =new OBJ_Hut();
        gp.obj[1].worldX= 1 * gp.tileSize;
        gp.obj[1].worldY= 6 * gp.tileSize;

        gp.obj[2] =new OBJ_Hut();
        gp.obj[2].worldX= 12 * gp.tileSize;
        gp.obj[2].worldY= 6 * gp.tileSize;

        gp.obj[3] =new OBJ_Castle();
        gp.obj[3].worldX= 6 * gp.tileSize;
        gp.obj[3].worldY= 6 * gp.tileSize;

        gp.obj[4] =new OBJ_market();
        gp.obj[4].worldX= 3 * gp.tileSize;
        gp.obj[4].worldY= 3 * gp.tileSize;

        gp.obj[5] =new OBJ_market();
        gp.obj[5].worldX= 6 * gp.tileSize;
        gp.obj[5].worldY= 3 * gp.tileSize;



        gp.obj[6] =new OBJ_House();
        gp.obj[6].worldX= 9 * gp.tileSize;
        gp.obj[6].worldY= 8 * gp.tileSize;

        gp.obj[7] =new OBJ_House();
        gp.obj[7].worldX= 5 * gp.tileSize;
        gp.obj[7].worldY= 10 * gp.tileSize;

        gp.obj[8] =new OBJ_market();
        gp.obj[8].worldX= 10 * gp.tileSize;
        gp.obj[8].worldY= 3 * gp.tileSize;

        gp.obj[9] =new OBJ_Trap();
        gp.obj[9].worldX= 8 * gp.tileSize;
        gp.obj[9].worldY= 10 * gp.tileSize;

        gp.obj[10] =new OBJ_Trap();
        gp.obj[10].worldX= 5 * gp.tileSize;
        gp.obj[10].worldY= 3 * gp.tileSize;

        gp.obj[11] =new OBJ_Trap();
        gp.obj[11].worldX= 3 * gp.tileSize;
        gp.obj[11].worldY= 10 * gp.tileSize;

        gp.obj[12] =new OBJ_chest();
        gp.obj[12].worldX= 10 * gp.tileSize;
        gp.obj[12].worldY= 10 * gp.tileSize;

        gp.obj[13] =new OBJ_Key();
        gp.obj[13].worldX= 7 * gp.tileSize;
        gp.obj[13].worldY= 9 * gp.tileSize;

        gp.obj[14] =new OBJ_Key();
        gp.obj[14].worldX= 4 * gp.tileSize;
        gp.obj[14].worldY= 7 * gp.tileSize;

        gp.obj[15] =new OBJ_Key();
        gp.obj[15].worldX= 4 * gp.tileSize;
        gp.obj[15].worldY= 9 * gp.tileSize;


        gp.obj[16] = new OBJ_JewelEncrustedSword();
        gp.obj[16].worldX = 5 * gp.tileSize; // Replace N with the next index and X with the desired grid position
        gp.obj[16].worldY = 9 * gp.tileSize; // Replace Y with the desired grid position

        gp.obj[17] = new OBJ_JewelEncrustedSword();
        gp.obj[17].worldX = 10 * gp.tileSize; // Replace N with the next index and X with the desired grid position
        gp.obj[17].worldY = 9 * gp.tileSize; // Replace Y with the desired grid position

        gp.obj[18] = new OBJ_Diamondring();
        gp.obj[18].worldX = 10 * gp.tileSize; // Example coordinates
        gp.obj[18].worldY = 5 * gp.tileSize;

        gp.obj[19] = new OBJ_Diamondring();
        gp.obj[19].worldX = 3 * gp.tileSize; // Example coordinates
        gp.obj[19].worldY = 5 * gp.tileSize;

        gp.obj[20] = new OBJ_woodenbow();
        gp.obj[20].worldX = 10 * gp.tileSize; // Example coordinates
        gp.obj[20].worldY = 3* gp.tileSize;

        gp.obj[21] = new OBJ_woodenbow();
        gp.obj[21].worldX = 5 * gp.tileSize; // Example coordinates
        gp.obj[21].worldY = 7* gp.tileSize;

        gp.obj[22] = new OBJ_CrystalGoblet();
        gp.obj[22].worldX = 3 * gp.tileSize; // Example coordinates
        gp.obj[22].worldY = 9 * gp.tileSize;

        gp.obj[23] = new OBJ_CrystalGoblet();
        gp.obj[23].worldX = 6 * gp.tileSize; // Example coordinates
        gp.obj[23].worldY = 9 * gp.tileSize;

        gp.obj[24] = new OBJ_dragonscroll();
        gp.obj[24].worldX = 10 * gp.tileSize; // Example coordinates
        gp.obj[24].worldY = 6 * gp.tileSize;

        gp.obj[25] = new OBJ_dragonscroll();
        gp.obj[25].worldX = 8 * gp.tileSize; // Example coordinates
        gp.obj[25].worldY = 6 * gp.tileSize;

        gp.obj[26] = new OBJ_GoldenGoblet();
        gp.obj[26].worldX = 7 * gp.tileSize; // Example coordinates
        gp.obj[26].worldY = 7 * gp.tileSize;

        gp.obj[27] = new OBJ_GoldenGoblet();
        gp.obj[27].worldX = 8 * gp.tileSize; // Example coordinates
        gp.obj[27].worldY = 8 * gp.tileSize;

        gp.obj[28] = new OBJ_paladinShield();
        gp.obj[28].worldX = 2 * gp.tileSize; // Example coordinates
        gp.obj[28].worldY = 5 * gp.tileSize;

        gp.obj[29] = new OBJ_paladinShield();
        gp.obj[29].worldX = 9 * gp.tileSize; // Example coordinates
        gp.obj[29].worldY = 5 * gp.tileSize;

        gp.obj[30] =new OBJ_market();
        gp.obj[30].worldX= 4 * gp.tileSize;
        gp.obj[30].worldY= 3 * gp.tileSize;

        gp.obj[31] =new OBJ_market();
        gp.obj[31].worldX= 8 * gp.tileSize;
        gp.obj[31].worldY= 3 * gp.tileSize;

        gp.obj[32] = new OBJ_paladinShield();
        gp.obj[32].worldX = 8 * gp.tileSize; // Example coordinates
        gp.obj[32].worldY = 3 * gp.tileSize;

        gp.obj[33] = new OBJ_woodenbow();
        gp.obj[33].worldX = 3 * gp.tileSize; // Example coordinates
        gp.obj[33].worldY =3* gp.tileSize;

        gp.obj[34] = new OBJ_JewelEncrustedSword();
        gp.obj[34].worldX = 4 * gp.tileSize; // Replace N with the next index and X with the desired grid position
        gp.obj[34].worldY = 3 * gp.tileSize; // Replace Y with the desired grid position

        gp.obj[35] = new OBJ_JewelEncrustedSword();
        gp.obj[35].worldX = 6 * gp.tileSize; // Replace N with the next index and X with the desired grid position
        gp.obj[35].worldY = 3 * gp.tileSize; // Replace Y with the desired grid position

        gp.obj[35] = new OBJ_House();
        gp.obj[35].worldX = 5 * gp.tileSize; // Replace N with the next index and X with the desired grid position
        gp.obj[35].worldY = 5 * gp.tileSize; // Replace Y with the desired grid position





    }
}
