package Game;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX / gp.tileSize;
        int entityRightCol = entityRightX / gp.tileSize;
        int entityTopRow = entityTopY / gp.tileSize;
        int entityBottomRow = entityBottomY / gp.tileSize;

        int tileNum1, tileNum2;
        switch (entity.direction) {
            case "hinten": //Valentin
                entityTopRow = (entityTopY - entity.speed) / gp.tileSize; //Valentin
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow]; //Valentin
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow]; //Valentin
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) { //Valentin
                    entity.collisionOn = true; //Valentin
                }
                break;
            case "gerade": //Valentin
                entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize; //Valentin
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow]; //Valentin
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow]; //Valentin
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) { //Valentin
                    entity.collisionOn = true; //Valentin
                }
                break;
            case "links": //Valentin
                entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize; //Valentin
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow]; //Valentin
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow]; //Valentin
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) { //Valentin
                    entity.collisionOn = true; //Valentin
                }
                break;
            case "rechts": //Valentin
                entityRightCol = (entityRightX + entity.speed) / gp.tileSize; //Valentin
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow]; //Valentin
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow]; //Valentin
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) { //Valentin
                    entity.collisionOn = true; //Valentin
                }
                break;
        }
    }

    public int checkObjekt(Entity entity, boolean player) {
        int index = 999; //Valentin

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                entity.solidArea.x = entity.x + entity.solidArea.x; //Valentin
                entity.solidArea.y = entity.y + entity.solidArea.y; //Valentin

                gp.obj[i].solidArea.x = gp.obj[i].X + gp.obj[i].solidArea.x; //Valentin
                gp.obj[i].solidArea.y = gp.obj[i].Y + gp.obj[i].solidArea.y; //Valentin

                switch (entity.direction) {
                    case "hinten": //Valentin
                        entity.solidArea.y -= entity.speed; //Valentin
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) { //Valentin
                            if (gp.obj[i].collision) { //Valentin
                                entity.collisionOn = true; //Valentin
                            }
                            if (player) { //Valentin
                                index = i; //Valentin
                            }
                        }
                        break;
                    case "gerade": //Valentin
                        entity.solidArea.y += entity.speed; //Valentin
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) { //Valentin
                            if (gp.obj[i].collision) { //Valentin
                                entity.collisionOn = true; //Valentin
                            }
                            if (player) { //Valentin
                                index = i; //Valentin
                            }
                        }
                        break;
                    case "links": //Valentin
                        entity.solidArea.x -= entity.speed; //Valentin
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) { //Valentin
                            if (gp.obj[i].collision) { //Valentin
                                entity.collisionOn = true; //Valentin
                            }
                            if (player) { //Valentin
                                index = i; //Valentin
                            }
                        }
                        break;
                    case "rechts": //Valentin
                        entity.solidArea.x += entity.speed; //Valentin
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) { //Valentin
                            if (gp.obj[i].collision) { //Valentin
                                entity.collisionOn = true; //Valentin
                            }
                            if (player) { //Valentin
                                index = i; //Valentin
                            }
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX; //Valentin
                entity.solidArea.y = entity.solidAreaDefaultY; //Valentin
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX; //Valentin
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY; //Valentin
            }
        }

        return index; //Valentin
    }
}
