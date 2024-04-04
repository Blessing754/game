package main;

import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;

    }
    public void setObject(){

        gp.obj[0] =new OBJ_Hut();
        gp.obj[0].worldX= 1 * gp.tileSize;
        gp.obj[0].worldY= 6 * gp.tileSize;

        gp.obj[1] =new OBJ_Hut();
        gp.obj[1].worldX= 12 * gp.tileSize;
        gp.obj[1].worldY= 6 * gp.tileSize;

        gp.obj[2] =new OBJ_Castle();
        gp.obj[2].worldX= 6 * gp.tileSize;
        gp.obj[2].worldY= 6 * gp.tileSize;

        gp.obj[3] =new OBJ_market();
        gp.obj[3].worldX= 3 * gp.tileSize;
        gp.obj[3].worldY= 3 * gp.tileSize;

        gp.obj[4] =new OBJ_market();
        gp.obj[4].worldX= 6 * gp.tileSize;
        gp.obj[4].worldY= 3 * gp.tileSize;



        gp.obj[5] =new OBJ_House();
        gp.obj[5].worldX= 9 * gp.tileSize;
        gp.obj[5].worldY= 8 * gp.tileSize;

        gp.obj[6] =new OBJ_House();
        gp.obj[6].worldX= 5 * gp.tileSize;
        gp.obj[6].worldY= 10 * gp.tileSize;

        gp.obj[7] =new OBJ_market();
        gp.obj[7].worldX= 10 * gp.tileSize;
        gp.obj[7].worldY= 3 * gp.tileSize;

        gp.obj[8] =new OBJ_Trap();
        gp.obj[8].worldX= 8 * gp.tileSize;
        gp.obj[8].worldY= 10 * gp.tileSize;

        gp.obj[9] =new OBJ_Trap();
        gp.obj[9].worldX= 5 * gp.tileSize;
        gp.obj[9].worldY= 3 * gp.tileSize;

        gp.obj[10] =new OBJ_Trap();
        gp.obj[10].worldX= 3 * gp.tileSize;
        gp.obj[10].worldY= 10 * gp.tileSize;

        gp.obj[11] =new OBJ_Chest();
        gp.obj[11].worldX= 10 * gp.tileSize;
        gp.obj[11].worldY= 10 * gp.tileSize;

        gp.obj[12] =new OBJ_Chest();
        gp.obj[12].worldX= 5 * gp.tileSize;
        gp.obj[12].worldY= 4 * gp.tileSize;

        gp.obj[13] =new OBJ_Chest();
        gp.obj[13].worldX= 3 * gp.tileSize;
        gp.obj[13].worldY= 4 * gp.tileSize;

        gp.obj[14] =new OBJ_Chest();
        gp.obj[14].worldX= 11 * gp.tileSize;
        gp.obj[14].worldY= 10 * gp.tileSize;

        gp.obj[15] =new OBJ_Chest();
        gp.obj[15].worldX= 11 * gp.tileSize;
        gp.obj[15].worldY= 5 * gp.tileSize;

        gp.obj[16] =new OBJ_Chest();
        gp.obj[16].worldX= 8 * gp.tileSize;
        gp.obj[16].worldY= 3 * gp.tileSize;

        gp.obj[17] =new OBJ_market();
        gp.obj[17].worldX= 4 * gp.tileSize;
        gp.obj[17].worldY= 3 * gp.tileSize;

        gp.obj[17] =new OBJ_market();
        gp.obj[17].worldX= 8 * gp.tileSize;
        gp.obj[17].worldY= 3 * gp.tileSize;


        gp.obj[18] = new OBJ_House();
        gp.obj[18].worldX = 5 * gp.tileSize; // Replace N with the next index and X with the desired grid position
        gp.obj[18].worldY = 5 * gp.tileSize; // Replace Y with the desired grid position


        gp.obj[19] =new OBJ_Chest();
        gp.obj[19].worldX= 11 * gp.tileSize;
        gp.obj[19].worldY= 2 * gp.tileSize;

        gp.obj[20] =new OBJ_Chest();
        gp.obj[20].worldX= 11 * gp.tileSize;
        gp.obj[20].worldY= 7 * gp.tileSize;

        gp.obj[21] =new OBJ_Chest();
        gp.obj[21].worldX= 4* gp.tileSize;
        gp.obj[21].worldY= 7 * gp.tileSize;

    }
}
