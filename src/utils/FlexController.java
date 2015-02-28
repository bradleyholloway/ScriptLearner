package utils;

/**
 * 10/8/2014
 */
public class FlexController {
    public XboxController controller;
    private ActiveButton jump;
    private ActiveButton spell;
    private ActiveButton rightTrigger;
    private ActiveButton shoot;
    private ActiveButton menuUp;
    private ActiveButton menuDown;

    public FlexController(int index) {
        jump = new ActiveButton();
        spell = new ActiveButton();
        rightTrigger = new ActiveButton();
        shoot = new ActiveButton();
        menuUp = new ActiveButton();
        menuDown = new ActiveButton();
        XboxController.loadControllers();
        switch (index) {
            case 1:
                controller = XboxController.controller1;
                break;
            case 2:
                controller = XboxController.controller2;
                break;
            case 3:
                controller = XboxController.controller3;
                break;
            case 4:
                controller = XboxController.controller4;
                break;
            default:
                controller = XboxController.controller1;
                break;
        }

    }

    public boolean isConnected() {
        return controller.backController.isConnected();
    }


    public void update() {
        jump.update(controller.isBottomAction());
        shoot.update(controller.isLeftAction());
        rightTrigger.update(controller.getRightTrigger() > .6);
        spell.update(controller.isRightAction());
        menuUp.update(controller.getLeftY() > .6);
        menuDown.update(controller.getLeftY() < -.6);
    }

    public boolean getJump() {
        return jump.get();
    }

    public boolean getShoot() {
        return shoot.get();
    }

    public boolean getRightTrigger() {
        return rightTrigger.get();
    }

    public boolean getSpell() {
        return spell.get();
    }

    public double movementX() {
        return controller.getLeftX();
    }

    public double aimDirection() {
        return (Math.round(controller.getLeftDir() * 4 / Math.PI) % 8) * Math.PI / 4;
    }

    public boolean getMenuUp() {
        return menuUp.get();
    }

    public boolean getMenuDown() {
        return menuDown.get();
    }

    public boolean getMenuEnter() {
        return jump.get();
    }
}
