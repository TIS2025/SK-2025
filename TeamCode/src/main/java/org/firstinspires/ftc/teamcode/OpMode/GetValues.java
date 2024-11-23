package org.firstinspires.ftc.teamcode.OpMode;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Sequence.GetValuesInitSequence;
import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

@TeleOp(name = "GetValues", group = "TeleOp")
public class GetValues extends LinearOpMode {

    private MecanumDrive mecanumDrive=null;
    private RobotHardware robot;

    private Intake intake;
    private Outtake outtake;
    private Hang hanger;

    // todo: Toggle state variables
    private boolean aToggle = false;
    private boolean bToggle = false;
    private boolean xToggle = false;
    private boolean yToggle = false;
    private boolean dpadUpToggle = false;
    private boolean dpadDownToggle = false;
    private boolean dpadLeftToggle = false;
    private boolean dpadRightToggle = false;
    private boolean leftBumperToggle = false;
    private boolean rightBumperToggle = false;
    private boolean leftTriggerToggle = false;
    private boolean rightTriggerToggle = false;
    private boolean leftStickButtonToggle = false;
    private boolean rightStickButtonToggle = false;
    private boolean startToggle = false;
    private boolean backToggle = false;

    @Override
    public void runOpMode() {

        robot = new RobotHardware();
        robot.init(hardwareMap,telemetry);
        intake = new Intake(robot);
        outtake = new Outtake(robot);
        hanger = new Hang(robot);
        mecanumDrive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));


        telemetry.addData("Status", "Initialized");
        telemetry.update();


        if(opModeInInit()){
            new GetValuesInitSequence(intake,outtake,hanger);
        }

        waitForStart();

        while (opModeIsActive()) {
            double drive = -gamepad1.left_stick_y*0.8;
            double strafe = -gamepad1.left_stick_x*0.8;
            double turn = -gamepad1.right_stick_x*0.5;

            PoseVelocity2d driveCommand = new PoseVelocity2d(new Vector2d(drive, strafe), turn);
            mecanumDrive.setDrivePowers(driveCommand);

            // Todo: Arm
            if (gamepad1.a && !aToggle)
            {
                robot.arm.setPosition(robot.arm.getPosition() + 0.01);
                aToggle = true;
            }
            else if (!gamepad1.a)
            {
                aToggle = false;
            }

            if (gamepad1.y && !yToggle)
            {
                robot.arm.setPosition(robot.arm.getPosition() - 0.05);
                yToggle = true;
            }
            else if (!gamepad1.y)
            {
                yToggle = false;
            }

            // Todo: Bucket control
            if (gamepad1.x && !xToggle)
            {
                robot.bucket.setPosition(robot.bucket.getPosition() + 0.05);
                xToggle = true;
            }
            else if (!gamepad1.x)
            {
                xToggle = false;
            }

            if (gamepad1.b && !bToggle)
            {
                robot.bucket.setPosition(robot.bucket.getPosition() - 0.05);
                bToggle = true;
            }
            else if (!gamepad1.b)
            {
                bToggle = false;
            }

            //Todo: Lifter control
            if (gamepad1.dpad_up && !dpadUpToggle)
            {
                robot.lifter.setTargetPosition(robot.lifter.getCurrentPosition() + 50);
                robot.lifter2.setTargetPosition(robot.lifter2.getCurrentPosition() + 50);
                robot.lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.lifter2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.lifter.setPower(1.0);
                robot.lifter2.setPower(1.0);
                dpadUpToggle = true;
            }
            else if (!gamepad1.dpad_up)
            {
                dpadUpToggle = false;
            }

            if (gamepad1.dpad_down && !dpadDownToggle)
            {
                robot.lifter.setTargetPosition(robot.lifter.getCurrentPosition() - 50);
                robot.lifter2.setTargetPosition(robot.lifter2.getCurrentPosition() - 50);
                robot.lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.lifter2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.lifter.setPower(1.0);
                robot.lifter2.setPower(1.0);
                dpadDownToggle = true;
            } else if (!gamepad1.dpad_down) {
                dpadDownToggle = false;
            }

            //Todo: Hanger
            if (gamepad2.dpad_up ) {
                robot.leftHang.setTargetPosition(robot.leftHang.getCurrentPosition() + 30);
                robot.leftHang.setPower(1.0);
            }
            if (gamepad2.dpad_down ) {
                robot.leftHang.setTargetPosition(robot.leftHang.getCurrentPosition() - 30);
                robot.leftHang.setPower(1.0);

            }

            // Todo: Yaw Arm control
            if (gamepad1.dpad_left && !dpadLeftToggle) {
                robot.yawArm.setPosition(robot.yawArm.getPosition() + 0.05);
                dpadLeftToggle = true;
            } else if (!gamepad1.dpad_left) {
                dpadLeftToggle = false;
            }

            if (gamepad1.dpad_right && !dpadRightToggle) {
                robot.yawArm.setPosition(robot.yawArm.getPosition() - 0.05);
                dpadRightToggle = true;
            } else if (!gamepad1.dpad_right) {
                dpadRightToggle = false;
            }

            // Todo: Specimen control
            if (gamepad1.left_bumper && !leftBumperToggle) {
                robot.specimen.setPosition(robot.specimen.getPosition() + 0.05);
                leftBumperToggle = true;
            } else if (!gamepad1.left_bumper) {
                leftBumperToggle = false;
            }

            if (gamepad1.right_bumper && !rightBumperToggle) {
                robot.specimen.setPosition(robot.specimen.getPosition() - 0.05);
                rightBumperToggle = true;
            } else if (!gamepad1.right_bumper) {
                rightBumperToggle = false;
            }

            // Todo: Elbow control
            if (gamepad1.left_trigger > 0.5 && !leftTriggerToggle) {
                robot.elbow1.setPosition(robot.elbow1.getPosition() + 0.05);
                robot.elbow2.setPosition(robot.elbow2.getPosition() + 0.05);
                leftTriggerToggle = true;
            } else if (gamepad1.left_trigger <= 0.5) {
                leftTriggerToggle = false;
            }

            if (gamepad1.right_trigger > 0.5 && !rightTriggerToggle) {
                robot.elbow1.setPosition(robot.elbow1.getPosition() - 0.05);
                robot.elbow2.setPosition(robot.elbow2.getPosition() - 0.05);
                rightTriggerToggle = true;
            } else if (gamepad1.right_trigger <= 0.5) {
                rightTriggerToggle = false;
            }

            // Todo: Hang control
            if (gamepad1.left_stick_button && !leftStickButtonToggle) {
                robot.leftHang.setTargetPosition(robot.leftHang.getCurrentPosition() + 50);

                robot.leftHang.setPower(1.0);

                leftStickButtonToggle = true;
            } else if (!gamepad1.left_stick_button) {
                leftStickButtonToggle = false;
            }

            if (gamepad1.right_stick_button && !rightStickButtonToggle) {
                robot.leftHang.setTargetPosition(robot.leftHang.getCurrentPosition() - 50);

                robot.leftHang.setPower(1.0);

                rightStickButtonToggle = true;
            } else if (!gamepad1.right_stick_button) {
                rightStickButtonToggle = false;
            }

            // Todo: Intake motor control
            if (gamepad1.start && !startToggle) {
                robot.intakeMotor.setPower(Math.min(1.0, robot.intakeMotor.getPower() + 0.1));
                startToggle = true;
            } else if (!gamepad1.start) {
                startToggle = false;
            }

            if (gamepad1.back && !backToggle) {
                robot.intakeMotor.setPower(Math.max(-1.0, robot.intakeMotor.getPower() - 0.1));
                backToggle = true;
            } else if (!gamepad1.back) {
                backToggle = false;
            }

            if (gamepad1.start && !startToggle &&gamepad1.left_bumper) {
                robot.intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.intakeMotor.setPower(Math.min(1.0, robot.intakeMotor.getPower() + 0.1));
                startToggle = true;
            } else if (!gamepad1.start) {
                startToggle = false;
            }

            if (gamepad1.back && !backToggle && gamepad1.left_bumper) {
                robot.intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.intakeMotor.setPower(Math.max(-1.0, robot.intakeMotor.getPower() - 0.1));
                backToggle = true;
            } else if (!gamepad1.back) {
                backToggle = false;
            }

            // Todo: Telemetry
            telemetry.addData("Intake Motor Power", robot.intakeMotor.getPower());
            telemetry.addData("Yaw Arm Position", robot.yawArm.getPosition());
            telemetry.addData("Arm Position", robot.arm.getPosition());
            telemetry.addData("Specimen Position", robot.specimen.getPosition());
            telemetry.addData("Elbow Position", robot.elbow1.getPosition());
            telemetry.addData("Elbow Position", robot.elbow2.getPosition());
            telemetry.addData("Lifter Position", robot.lifter.getCurrentPosition());
            telemetry.addData("Lifter2 Position", robot.lifter2.getCurrentPosition());
            telemetry.addData("Bucket Position", robot.bucket.getPosition());
            telemetry.addData("Left Hang Position", robot.leftHang.getCurrentPosition());

            telemetry.addData("Left Hang Position", robot.leftHang.getCurrentPosition());
            telemetry.addData("LeftFront Motor Current", mecanumDrive.leftFront.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("LeftRear Motor Current", mecanumDrive.leftFront.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("RightFront Current", mecanumDrive.leftFront.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("RightRear Motor Current", mecanumDrive.leftFront.getCurrent(CurrentUnit.AMPS));


            telemetry.addData("Dekho Ye Current1:",robot.lifter.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("Dekho Ye Current2:",robot.lifter2.getCurrent(CurrentUnit.AMPS));

            telemetry.update();
        }
    }
}
