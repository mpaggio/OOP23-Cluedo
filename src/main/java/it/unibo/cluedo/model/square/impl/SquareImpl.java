package it.unibo.cluedo.model.square.impl;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.model.square.api.Effect.EffectType;
import it.unibo.cluedo.utilities.Position;

public class SquareImpl implements Square {
    private Position position;
    private EffectType effect;

    public SquareImpl(final Position position, final EffectType effect) {
        this.position = position; 
        this.effect = effect;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void landOn(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'landOn'");    
    }

    @Override
    public EffectType getEffect() {
        return this.effect;
    }

    @Override
    public void setEffect(EffectType effect) {
        this.effect = effect;
    }
    
}
