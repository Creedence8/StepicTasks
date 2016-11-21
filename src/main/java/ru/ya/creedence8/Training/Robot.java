package ru.ya.creedence8.Training;

/**
 * Created by Cole S' Offe on 10.11.2016.
 */
public class Robot {
    public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
        boolean ok = false;
        for (int i=0;!ok && i<3; i++){
            try (RobotConnection nrc = robotConnectionManager.getConnection()){
                nrc.moveRobotTo(toX, toY);
                ok = true;
            } catch (RobotConnectionException e){
                if (ok) {}
                else {if (i>1) throw e;}
            } catch (Exception e){
                throw e;
            }
        }

    }
}

interface RobotConnection extends AutoCloseable {
    void moveRobotTo(int x, int y);
    @Override
    void close();
}

interface RobotConnectionManager {
    RobotConnection getConnection();
}

class RobotConnectionException extends RuntimeException {

    public RobotConnectionException(String message) {
        super(message);

    }

    public RobotConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}