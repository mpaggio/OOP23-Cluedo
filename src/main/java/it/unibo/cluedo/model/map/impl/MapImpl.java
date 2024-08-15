package it.unibo.cluedo.model.map.impl;

import java.util.List;

import it.unibo.cluedo.model.map.api.Map;
import it.unibo.cluedo.model.room.api.MapComponent;
import it.unibo.cluedo.model.room.impl.RoomImpl;
import it.unibo.cluedo.model.square.impl.NoEffectImpl;
import it.unibo.cluedo.model.square.impl.SquareImpl;
import it.unibo.cluedo.utilities.Position;

public class MapImpl implements Map {
    private static final int MAP_HEIGHT = 25;
    private static final int MAP_WIDTH = 24;
    private static final int[][] MAP_TILES_DISPOSITION = new int[][]
        {
            {0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
            {2,2,2,2,2,4,0,1,1,1,5,5,5,5,1,1,1,0,6,6,6,6,6,6},
            {2,2,2,2,2,2,1,1,5,5,5,5,5,5,5,5,1,1,6,6,6,6,6,6},
            {2,2,2,2,2,2,1,1,5,5,5,5,5,5,5,5,1,1,6,6,6,6,6,6},
            {2,2,2,2,2,2,1,1,5,5,5,5,5,5,5,5,1,1,3,6,6,6,6,6},
            {2,2,2,2,2,2,1,1,3,5,5,5,5,5,5,3,1,1,1,6,6,6,4,0},
            {0,2,2,2,3,2,1,1,5,5,5,5,5,5,5,5,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,5,3,5,5,5,5,3,5,1,1,1,1,1,1,1,0},
            {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,7,7,7,7,7,7},
            {12,12,12,12,12,1,1,1,1,1,1,1,1,1,1,1,1,1,3,7,7,7,7,7},
            {12,12,12,12,12,12,12,12,1,1,2,2,2,2,2,1,1,1,7,7,7,7,7,7},
            {12,12,12,12,12,12,12,12,1,1,2,2,2,2,2,1,1,1,7,7,7,7,7,7},
            {12,12,12,12,12,12,12,12,1,1,2,2,2,2,2,1,1,1,7,7,7,7,3,7},
            {12,12,12,12,12,12,12,3,1,1,2,2,2,2,2,1,1,1,1,1,1,1,1,0},
            {12,12,12,12,12,12,12,12,1,1,2,2,2,2,2,1,1,1,8,8,3,8,8,0},
            {12,12,12,12,12,12,12,12,1,1,2,2,2,2,2,1,1,8,8,8,8,8,8,8},
            {12,12,12,12,12,12,3,12,1,1,2,2,2,2,2,1,1,3,8,8,8,8,8,8},
            {0,1,1,1,1,1,1,1,1,1,2,2,3,2,2,1,1,8,8,8,8,8,8,8},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,8,8,8,8,8,0},
            {0,1,1,1,1,1,1,1,1,10,10,3,3,10,10,1,1,1,1,1,1,1,1,1},
            {4,11,11,11,11,11,3,1,1,10,10,10,10,10,10,1,1,1,1,1,1,1,1,0},
            {11,11,11,11,11,11,11,1,1,10,10,10,10,10,10,1,1,3,9,9,9,9,9,4},
            {11,11,11,11,11,11,11,1,1,10,10,10,10,10,10,1,1,9,9,9,9,9,9,9},
            {11,11,11,11,11,11,11,1,1,10,10,10,10,10,10,1,1,9,9,9,9,9,9,9},
            {11,11,11,11,11,11,0,1,0,10,10,10,10,10,10,0,1,0,9,9,9,9,9,9}
        };  
    private List<MapComponent> tiles;

    private enum RoomType {
        KITCHEN(2, "Kitchen"),
        BALLROOM(5, "Ballroom"),
        CONSERVATORY(6, "Conservatory"),
        BILLIARD_ROOM(7, "Billiard room"),
        LIBRARY(8, "Library"),
        STUDY(9, "Study"),
        HALL(10, "Hall"),
        LOUNGE(11, "Lounge"),
        DINING_ROOM(12, "Dining room");

        private final int code;
        private final String name;

        RoomType(final int code, final String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return this.code;
        }

        public String getName() {
            return this.name;
        }

        public static RoomType fromCode(final int code) {
            for(RoomType type : values()){
                if(type.getCode() == code) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid room code: " + code);
        }
    }

    public MapImpl() {
        final RoomImpl[] rooms = new RoomImpl[RoomType.values().length];
        // Initialising rooms
        for(RoomType type : RoomType.values()) {
            rooms[type.ordinal()] =  new RoomImpl(type.getName());
            this.tiles.add(rooms[type.ordinal()]);
        }
        // Adding squares and assigning to rooms if necessary
        for(int i = 0; i < MAP_HEIGHT; i ++) {
            for(int j = 0; j < MAP_WIDTH; j++) {
                final int tileType =  MAP_TILES_DISPOSITION[i][j];
                if(tileType == 1) {
                    tiles.add(new SquareImpl(new Position(i, j), new NoEffectImpl()));
                }
                else {
                    rooms[RoomType.fromCode(tileType).ordinal()].addSquare(
                        new SquareImpl(new Position(i, j), new NoEffectImpl())
                    ); 
                }
            }
        } 
    }
}
