package org.firstinspires.ftc.teamcode.OpMode;//package org.firstinspires.ftc.teamcode.OpMode;
//
//import com.acmerobotics.dashboard.config.Config;
//import com.arcrobotics.ftclib.controller.PIDController;
//import com.arcrobotics.ftclib.controller.wpilibcontroller.ElevatorFeedforward;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//
//@TeleOp
//@Config
//public class PIDLifter extends LinearOpMode {
//    public static double ks = 0;
//    public static double kg = 0;
//    public static double kv = 0;
//    public static double ka = 0;
//    public static double kp = 0;
//    public static double kd = 0;
//    public static double ki = 0;
//    public static double targetPosition = 0;
//    public DcMotorEx elevator = null;
//
//    ElevatorFeedforward elevatorFeedforward = new ElevatorFeedforward(ks,kg,kv,ka);
//
//    PIDController pidController = new PIDController(kp, ki, kd);
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        elevator = hardwareMap.get(DcMotorEx.class, "elevator");
//        elevator.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
////        elevator.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
//        elevator.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
////        elevator.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
//        waitForStart();
//
//        while (opModeIsActive()) {
//
////            elevator.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
//            elevator.setPower(pidController.calculate(elevator.getCurrentPosition(), targetPosition));
//            elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//
//            telemetry.addData("Position", elevator.getCurrentPosition());
//
//
//            if (gamepad1.a) {
//                targetPosition += 50;
//            } else if (gamepad1.b) {
//                targetPosition -= 50;
//            }
//
//            if (gamepad1.x) {
//                targetPosition = 2000;
//            }
//
//            telemetry.update();
//
//        }
//    }
//}
