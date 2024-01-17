// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  public static class Hardware {
    CANSparkMax lMasterMotor;
    CANSparkMax rMasterMotor;
    CANSparkMax lSlaveMotor;
    CANSparkMax rSlaveMotor;

    public Hardware(CANSparkMax lMasterMotor,
                  CANSparkMax rMasterMotor,
                  CANSparkMax lSlaveMotor,
                  CANSparkMax rSlaveMotor) {
      this.lMasterMotor = lMasterMotor;
      this.rMasterMotor = rMasterMotor;
      this.lSlaveMotor = lSlaveMotor;
      this.rSlaveMotor = rSlaveMotor;
    }
  }

  private CANSparkMax m_lMasterMotor;
  private CANSparkMax m_rMasterMotor;
  private CANSparkMax m_lSlaveMotor;
  private CANSparkMax m_rSlaveMotor;

  private DifferentialDrive m_driveTrain;

  /** Creates a new Subsystem. */
  public DriveSubsystem(Hardware driveTrainHardware) {
    this.m_lMasterMotor = driveTrainHardware.lMasterMotor;
    this.m_rMasterMotor = driveTrainHardware.rMasterMotor;
    this.m_lSlaveMotor = driveTrainHardware.lSlaveMotor;
    this.m_rSlaveMotor = driveTrainHardware.rSlaveMotor;

    m_driveTrain = new DifferentialDrive(m_lMasterMotor, m_rMasterMotor);

    m_rSlaveMotor.setInverted(true);
    m_rMasterMotor.setInverted(true);

    m_lSlaveMotor.follow(m_lMasterMotor);
    m_rSlaveMotor.follow(m_rMasterMotor);
  }

  public static Hardware initializeHardware() {
    Hardware driveTrainHardware = new Hardware(
      new CANSparkMax(Constants.DriveHardware.LEFT_FRONT_MASTER_MOTOR, MotorType.kBrushless),
      new CANSparkMax(Constants.DriveHardware.RIGHT_FRONT_MASTER_MOTOR, MotorType.kBrushless),
      new CANSparkMax(Constants.DriveHardware.LEFT_REAR_SLAVE_MOTOR, MotorType.kBrushless),
      new CANSparkMax(Constants.DriveHardware.LEFT_REAR_SLAVE_MOTOR, MotorType.kBrushless));

    return driveTrainHardware;
  }

  public void set(double speed, double turn) {
    m_driveTrain.arcadeDrive(speed, turn);
  }
  
  public void stop() {
    m_driveTrain.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}