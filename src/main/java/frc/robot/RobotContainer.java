// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.AutoDoNothing;
import frc.robot.commands.Demo;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private final int kDriverControlerPort = 0;

  private final XboxController m_controller = new XboxController(kDriverControlerPort);
  
  // The robot's subsystems and commands are defined here...
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Elevator m_elevator = new Elevator();

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    System.out.println("RobotContainer()");
    LiveWindow.enableAllTelemetry();

    // Configure the trigger bindings
    configureBindings();

    // Set default Teleop command
    System.out.println("default Teleop command");
    m_drivetrain.setDefaultCommand(
      new RunCommand(() -> 
        m_drivetrain.drive(m_controller.getLeftY(), m_controller.getRightX()), 
        m_drivetrain));

    // Setup autonomous select commands
    System.out.println("Setup autonomous select command");
    m_chooser = new SendableChooser<>();
    m_chooser.setDefaultOption("Autonomous Position 3", new Demo(m_drivetrain));
    m_chooser.addOption("Do nothing", new AutoDoNothing());
    SmartDashboard.putData(m_chooser);

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    System.out.println("configureBindings");

    // new XboxController(7).onTrue (new InstantCommand(m_elevator::getMeasurement, m_elevator));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    System.out.println("getAutonomousCommand()");
    // An example command will be run in autonomous
    return m_autonomousCommand = m_chooser.getSelected();
  }
}
