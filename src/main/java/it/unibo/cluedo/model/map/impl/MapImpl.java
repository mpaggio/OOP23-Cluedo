package it.unibo.cluedo.model.map.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import it.unibo.cluedo.model.map.api.Map;
import it.unibo.cluedo.model.room.api.MapComponent;
import it.unibo.cluedo.model.room.api.MapComponentVisitor;
import it.unibo.cluedo.model.room.impl.MapComponentVisitorImpl;
import it.unibo.cluedo.model.room.impl.RoomImpl;
import it.unibo.cluedo.model.square.impl.BonusEffectImpl;
import it.unibo.cluedo.model.square.impl.MalusEffectImpl;
import it.unibo.cluedo.model.square.impl.SquareFactory;
import it.unibo.cluedo.model.trapdoor.api.TrapDoor;
import it.unibo.cluedo.model.trapdoor.impl.TrapDoorImpl;
import it.unibo.cluedo.utilities.Position;

/**
 * Implementation of the Map interface for the Cluedo game.
 * This class represents the game map and initializes the rooms and squares based
 * on a predefined layout.
 */
public class MapImpl implements Map {
    private static final int MAP_HEIGHT = 25;
    private static final int MAP_WIDTH = 24;
    private static final int MAX_SQUARE_WITH_EFFECT = 3;
    // square(1), entrance(3), trapdoor(4)
    private static final int[][] MAP_TILES_DISPOSITION = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {2, 2, 2, 2, 2, 4, 0, 1, 1, 1, 5, 5, 5, 5, 1, 1, 1, 0, 6, 6, 6, 6, 6, 6},
        {2, 2, 2, 2, 2, 2, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 6, 6, 6, 6, 6, 6},
        {2, 2, 2, 2, 2, 2, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 6, 6, 6, 6, 6, 6},
        {2, 2, 2, 2, 2, 2, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 3, 6, 6, 6, 6, 6},
        {2, 2, 2, 2, 2, 2, 1, 1, 3, 5, 5, 5, 5, 5, 5, 3, 1, 1, 1, 6, 6, 6, 4, 0},
        {0, 2, 2, 2, 3, 2, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 5, 3, 5, 5, 5, 5, 3, 5, 1, 1, 1, 1, 1, 1, 1, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 7, 7, 7, 7, 7, 7},
        {12, 12, 12, 12, 12, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 7, 7, 7, 7, 7},
        {12, 12, 12, 12, 12, 12, 12, 12, 1, 1, 13, 13, 13, 13, 13, 1, 1, 1, 7, 7, 7, 7, 7, 7},
        {12, 12, 12, 12, 12, 12, 12, 12, 1, 1, 13, 13, 13, 13, 13, 1, 1, 1, 7, 7, 7, 7, 7, 7},
        {12, 12, 12, 12, 12, 12, 12, 12, 1, 1, 13, 13, 13, 13, 13, 1, 1, 1, 7, 7, 7, 7, 3, 7},
        {12, 12, 12, 12, 12, 12, 12, 3, 1, 1, 13, 13, 13, 13, 13, 1, 1, 1, 1, 1, 1, 1, 1, 0},
        {12, 12, 12, 12, 12, 12, 12, 12, 1, 1, 13, 13, 13, 13, 13, 1, 1, 1, 8, 8, 3, 8, 8, 0},
        {12, 12, 12, 12, 12, 12, 12, 12, 1, 1, 13, 13, 13, 13, 13, 1, 1, 8, 8, 8, 8, 8, 8, 8},
        {12, 12, 12, 12, 12, 12, 3, 12, 1, 1, 13, 13, 3, 13, 13, 1, 1, 3, 8, 8, 8, 8, 8, 8},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 8, 8, 8, 8, 8, 8, 8},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 10, 10, 3, 3, 10, 10, 1, 1, 1, 8, 8, 8, 8, 8, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 10, 10, 10, 10, 10, 10, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {4, 11, 11, 11, 11, 11, 3, 1, 1, 10, 10, 10, 10, 10, 10, 1, 1, 1, 1, 1, 1, 1, 1, 0},
        {11, 11, 11, 11, 11, 11, 11, 1, 1, 10, 10, 10, 10, 10, 10, 1, 1, 3, 9, 9, 9, 9, 9, 4},
        {11, 11, 11, 11, 11, 11, 11, 1, 1, 10, 10, 10, 10, 10, 10, 1, 1, 9, 9, 9, 9, 9, 9, 9},
        {11, 11, 11, 11, 11, 11, 11, 1, 1, 10, 10, 10, 10, 10, 10, 1, 1, 9, 9, 9, 9, 9, 9, 9},
        {11, 11, 11, 11, 11, 11, 0, 1, 0, 10, 10, 10, 10, 10, 10, 0, 1, 0, 9, 9, 9, 9, 9, 9}
    };
    private static final Random RANDOM = new Random();
    private List<MapComponent> tiles;
    private MapComponentVisitor visitor;

    /**
     * Enum representing the different types of room in the Cluedo game.
     */
    private enum RoomType {
        KITCHEN(2, "Kitchen"),
        BALLROOM(5, "Ballroom"),
        CONSERVATORY(6, "Conservatory"),
        BILLIARD_ROOM(7, "Billiard room"),
        LIBRARY(8, "Library"),
        STUDY(9, "Study"),
        HALL(10, "Hall"),
        LOUNGE(11, "Lounge"),
        DINING_ROOM(12, "Dining room"),
        CLUEDO(13, "Cluedo");

        private final int code;
        private final String name;

        /**
         * Constructor for RoomType.
         * 
         * @param code the code of the room type
         * @param name the name of the room type
         */
        RoomType(final int code, final String name) {
            this.code = code;
            this.name = name;
        }

        /**
         * Gets the code of the room type.
         * 
         * @return the code of the room type 
         */
        public int getCode() {
            return this.code;
        }

        /**
         * Gets the name of the room type.
         * 
         * @return the name of the room type
         */
        public String getName() {
            return this.name;
        }

        /**
         * Returns the room type corresponding to the given code.
         * 
         * @param code the code of the room type
         * @return the RoomType corresponding to the given code
         * @throws IllegalArgumentException if the code does not correspond to any RoomType
         */
        public static RoomType fromCode(final int code) {
            for (final RoomType type : values()) {
                if (type.getCode() == code) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid room code: " + code);
        }
    }

    /**
     * Constructor for MapImpl.
     * Initializes the rooms and adds the square to the map based on the predefined layout.
     */
    public MapImpl() {
        final MapComponentVisitor visitor = new MapComponentVisitorImpl();
        final List<MapComponent> localTiles = new ArrayList<>();
        final RoomImpl[] rooms = new RoomImpl[RoomType.values().length];
        int bonusCount = 0;
        int malusCount = 0;
        // Initialising rooms
        for (final RoomType type : RoomType.values()) {
            rooms[type.ordinal()] =  new RoomImpl(type.getName());
            rooms[type.ordinal()].accept(visitor);
            localTiles.add(rooms[type.ordinal()]);
        }
        // Adding squares and assigning to rooms if necessary
        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                final int tileType =  MAP_TILES_DISPOSITION[i][j];
                if (tileType == 1) {
                    final MapComponent squareToAdd = createRandomSquare(
                        new Position(i, j),
                        bonusCount,
                        malusCount
                    );
                    squareToAdd.accept(visitor);
                    if (visitor.getLastVisitedSquare().get().getEffect() instanceof BonusEffectImpl) {
                        bonusCount++;
                    } else if (visitor.getLastVisitedSquare().get().getEffect() instanceof MalusEffectImpl) {
                        malusCount++;
                    }
                    localTiles.add(squareToAdd);
                } else if (tileType == 3) {
                    rooms[findRoomForEntrance(i, j).ordinal()].addEntrance(
                        SquareFactory.createNormalSquare(new Position(i, j))
                    );
                } else if (tileType == 4) {
                    final TrapDoor trapDoor = new TrapDoorImpl(
                        rooms[findConnectedRoomFromPosition(i, j).ordinal()],
                        new Position(i, j)
                    );
                    rooms[findRoomForEntrance(i, j).ordinal()].setTrapDoor(Optional.of(trapDoor));
                } else if (tileType != 0) {
                    rooms[RoomType.fromCode(tileType).ordinal()].addSquare(
                        SquareFactory.createNormalSquare(new Position(i, j))
                    ); 
                }
            }
        } 
        this.tiles = localTiles;
        this.visitor = visitor;
    }

    /**
     * Generate a random square, choosing from malus, bonus and normal.
     * 
     * @param position the position of the random square
     * @param bonusCount the count of created bonus square
     * @param malusCount the count of created malus square
     * @return the created map component
     */
    private MapComponent createRandomSquare(final Position position, final int bonusCount, final int malusCount) {
        if (bonusCount < MAX_SQUARE_WITH_EFFECT && malusCount < MAX_SQUARE_WITH_EFFECT) {
            final int randomValue = RANDOM.nextInt(3);
            switch (randomValue) {
                case 0:
                    return SquareFactory.createBonusSquare(position);
                case 1:
                    return SquareFactory.createMalusSquare(position);
                case 2:
                    return SquareFactory.createNormalSquare(position);
                default:
                    return SquareFactory.createNormalSquare(position);
            }
        } else if (bonusCount < MAX_SQUARE_WITH_EFFECT) {
            return SquareFactory.createBonusSquare(position);
        } else if (malusCount < MAX_SQUARE_WITH_EFFECT) {
            return SquareFactory.createMalusSquare(position);
        } else {
            return SquareFactory.createNormalSquare(position);
        }
    }

    /**
     * Finds the room type for the entrance at the given position.
     * 
     * @param i the row index of the entrance
     * @param j the column index of the entrance
     * @return the room type corresponding to the entrance position
     */
    private RoomType findRoomForEntrance(final int i, final int j) {
        if (i > 0
            && MAP_TILES_DISPOSITION[i - 1][j] != 1
            && MAP_TILES_DISPOSITION[i - 1][j] != 3
            && MAP_TILES_DISPOSITION[i - 1][j] != 0) {
            return RoomType.fromCode(MAP_TILES_DISPOSITION[i - 1][j]);
        } else if (i < MAP_HEIGHT - 1
            && MAP_TILES_DISPOSITION[i + 1][j] != 1
            && MAP_TILES_DISPOSITION[i + 1][j] != 3
            && MAP_TILES_DISPOSITION[i + 1][j] != 0) {
            return RoomType.fromCode(MAP_TILES_DISPOSITION[i + 1][j]);
        } else if (j > 0
            && MAP_TILES_DISPOSITION[i][j - 1] != 1
            && MAP_TILES_DISPOSITION[i][j - 1] != 3
            && MAP_TILES_DISPOSITION[i][j - 1] != 0) {
            return RoomType.fromCode(MAP_TILES_DISPOSITION[i][j - 1]);
        } else if (j < MAP_WIDTH - 1
            && MAP_TILES_DISPOSITION[i][j + 1] != 1
            && MAP_TILES_DISPOSITION[i][j + 1] != 3
            && MAP_TILES_DISPOSITION[i][j + 1] != 0) {
            return RoomType.fromCode(MAP_TILES_DISPOSITION[i][j + 1]);
        } else {
            return null;
        }
    }

    /**
     * Finds the room type connected to the trapdoor in the given position.
     * 
     * @param i the row index of the entrance
     * @param j the column index of the entrance
     * @return the room type connected to the trapdoor
     */
    private RoomType findConnectedRoomFromPosition(final int i, final int j) {
        if (i < MAP_HEIGHT / 2 && j < MAP_WIDTH / 2) {
            return RoomType.STUDY;
        } else if (i < MAP_HEIGHT / 2 && j > MAP_WIDTH / 2) {
            return RoomType.LOUNGE;
        } else if (i > MAP_HEIGHT / 2 && j < MAP_WIDTH / 2) {
            return RoomType.CONSERVATORY;
        } else if (i > MAP_HEIGHT / 2 && j > MAP_WIDTH / 2) {
            return RoomType.KITCHEN;
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MapComponent> getMap() {
        return List.copyOf(this.tiles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MapComponentVisitor getVisitor() {
        return this.visitor;
    }
}
