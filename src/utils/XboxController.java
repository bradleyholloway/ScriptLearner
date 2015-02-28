package utils;

import ch.aplu.xboxcontroller.XboxControllerAdapter;

import java.io.File;

/**
 * 10/8/2014
 */
public class XboxController {
    public static XboxController controller1 = null;
    public static XboxController controller2 = null;
    public static XboxController controller3 = null;
    public static XboxController controller4 = null;


    ch.aplu.xboxcontroller.XboxController backController;


    private double leftTrigger;
    private double rightTrigger;
    private double leftX;
    private double leftY;
    private double rightX;
    private double rightY;
    private double leftMag;
    private double leftDir;
    private double rightMag;
    private double rightDir;
    private boolean topAction;
    private boolean leftAction;
    private boolean bottomAction;
    private boolean rightAction;
    private boolean leftStick;
    private boolean rightStick;
    private boolean back;
    private boolean start;
    private boolean leftBumper;
    private boolean rightBumper;
    private boolean dPadDown;
    private boolean dPadUp;
    private boolean dPadLeft;
    private boolean dPadRight;

    public static void loadControllers() {
        if (controller1 == null) {
            controller1 = new XboxController(1);
        }
        if (controller1.backController.isConnected()) {
            if (controller2 == null) {
                controller2 = new XboxController(2);
            }
            if (controller2.backController.isConnected()) {
                if (controller3 == null) {
                    controller3 = new XboxController(3);
                }
                if (controller3.backController.isConnected()) {
                    if (controller4 == null) {
                        controller4 = new XboxController(4);
                    }
                }
            }
        }

    }


    private XboxController(int index) {
        File f = new File("lib/xboxcontroller64.dll");
        backController = new ch.aplu.xboxcontroller.XboxController(f.getAbsolutePath(), index, 50, 50);
        if (!backController.isConnected()) {
            backController.release();
        }

        backController.addXboxControllerListener(new XboxControllerAdapter() {
            @Override
            public void back(boolean pressed) {
                back = pressed;
            }

            @Override
            public void leftThumbDirection(double direction) {
                leftDir = direction * Math.PI / 180;
                leftDir = -leftDir + 2 * Math.PI;
                leftDir += Math.PI / 2;
                if (leftDir >= 2 * Math.PI) {
                    leftDir -= 2 * Math.PI;
                }
                leftX = leftMag * Math.cos(leftDir);
                leftY = leftMag * Math.sin(leftDir);
            }

            @Override
            public void buttonA(boolean pressed) {
                bottomAction = pressed;
            }

            @Override
            public void buttonB(boolean pressed) {
                rightAction = pressed;
            }

            @Override
            public void buttonX(boolean pressed) {
                leftAction = pressed;
            }

            @Override
            public void buttonY(boolean pressed) {
                topAction = pressed;
            }

            @Override
            public void leftShoulder(boolean pressed) {
                leftBumper = pressed;
            }

            @Override
            public void leftThumb(boolean pressed) {
                leftStick = pressed;
            }

            @Override
            public void leftThumbMagnitude(double magnitude) {
                leftMag = magnitude;
                leftX = leftMag * Math.cos(leftDir);
                leftY = leftMag * Math.sin(leftDir);
            }

            @Override
            public void leftTrigger(double value) {
                leftTrigger = value;
            }

            @Override
            public void rightShoulder(boolean pressed) {
                rightBumper = pressed;
            }

            @Override
            public void rightThumb(boolean pressed) {
                rightStick = pressed;
            }

            @Override
            public void rightThumbDirection(double direction) {
                rightDir = direction * Math.PI / 180;
                rightDir = -rightDir + 2 * Math.PI;
                rightDir += Math.PI / 2;
                if (rightDir >= 2 * Math.PI) {
                    rightDir -= 2 * Math.PI;
                }
                rightX = rightMag * Math.cos(rightDir);
                rightY = rightMag * Math.sin(rightDir);
            }

            @Override
            public void rightThumbMagnitude(double magnitude) {
                rightMag = magnitude;

                rightX = rightMag * Math.cos(rightDir);
                rightY = rightMag * Math.sin(rightDir);
            }

            @Override
            public void rightTrigger(double value) {
                rightTrigger = value;
            }

            @Override
            public void start(boolean pressed) {
                start = pressed;
            }

            @Override
            public void dpad(int direction, boolean pressed) {
                super.dpad(direction, pressed);
                dPadDown = false;
                dPadUp = false;
                dPadLeft = false;
                dPadRight = false;
                if (pressed) {
                    if (direction < Math.PI / 4 || direction > 7 * Math.PI / 4) {
                        dPadRight = true;
                    } else if (direction < Math.PI * 3 / 4) {
                        dPadUp = true;
                    } else if (direction < Math.PI * 5 / 4) {
                        dPadLeft = true;
                    } else if (direction < Math.PI * 7 / 4) {
                        dPadDown = true;
                    }
                }
            }
        });
    }
    public boolean isConnected() { return backController.isConnected(); }

    public double getLeftTrigger() {
        return leftTrigger;
    }

    public double getRightTrigger() {
        return rightTrigger;
    }

    public double getLeftX() {
        return leftX;
    }

    public double getLeftY() {
        return leftY;
    }

    public double getRightX() {
        return rightX;
    }

    public double getRightY() {
        return rightY;
    }

    public boolean isTopAction() {
        return topAction;
    }

    public boolean isLeftAction() {
        return leftAction;
    }

    public boolean isBottomAction() {
        return bottomAction;
    }

    public boolean isRightAction() {
        return rightAction;
    }

    public boolean isLeftStick() {
        return leftStick;
    }

    public boolean isRightStick() {
        return rightStick;
    }

    public boolean isBack() {
        return back;
    }

    public boolean isStart() {
        return start;
    }

    public boolean isLeftBumper() {
        return leftBumper;
    }

    public boolean isRightBumper() {
        return rightBumper;
    }

    public boolean isdPadDown() {
        return dPadDown;
    }

    public boolean isdPadUp() {
        return dPadUp;
    }

    public boolean isdPadLeft() {
        return dPadLeft;
    }

    public boolean isdPadRight() {
        return dPadRight;
    }

    public double getLeftMag() {
        return leftMag;
    }

    public double getLeftDir() {
        return leftDir;
    }

    public double getRightMag() {
        return rightMag;
    }

    public double getRightDir() {
        return rightDir;
    }
}
